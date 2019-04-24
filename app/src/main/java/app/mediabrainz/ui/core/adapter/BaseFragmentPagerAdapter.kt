package app.mediabrainz.ui.core.adapter

import android.os.Parcelable
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.viewpager.widget.PagerAdapter


abstract class BaseFragmentPagerAdapter(private val mFragmentManager: FragmentManager) : PagerAdapter() {

    private var mCurTransaction: FragmentTransaction? = null
    private var mCurrentPrimaryItem: Fragment? = null

    abstract fun getItem(position: Int): Fragment

    override fun startUpdate(container: ViewGroup) {
        if (container.id == -1) {
            throw IllegalStateException("ViewPager with adapter $this requires a view id")
        }
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        if (mCurTransaction == null) {
            mCurTransaction = mFragmentManager.beginTransaction()
        }

        val name = makeFragmentName(container.id, getItemId(position))
        var fragment = mFragmentManager.findFragmentByTag(name)

        if (fragment != null) {
            mCurTransaction?.attach(fragment)
        } else {
            fragment = getItem(position)
            mCurTransaction?.add(container.id, fragment, name)
        }

        if (fragment !== mCurrentPrimaryItem) {
            fragment.setMenuVisibility(false)
            fragment.userVisibleHint = false
        }
        return fragment
    }

    fun removePagerFragments(container: ViewGroup) {
        if (mCurTransaction == null) {
            mCurTransaction = mFragmentManager.beginTransaction()
        }
        for (i in 0 until count) {
            val name = makeFragmentName(container.id, getItemId(i))
            val fragment = mFragmentManager.findFragmentByTag(name)
            if (fragment != null) {
                mCurTransaction!!.remove(fragment)
            }
        }
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        if (mCurTransaction == null) {
            mCurTransaction = this.mFragmentManager.beginTransaction()
        }
        mCurTransaction!!.detach(`object` as Fragment)
    }

    override fun setPrimaryItem(container: ViewGroup, position: Int, obj: Any) {
        val fragment = obj as Fragment
        if (fragment !== mCurrentPrimaryItem) {
            mCurrentPrimaryItem?.let {
                it.setMenuVisibility(false)
                it.userVisibleHint = false
            }
            fragment.setMenuVisibility(true)
            fragment.userVisibleHint = true
            mCurrentPrimaryItem = fragment
        }

    }

    override fun finishUpdate(container: ViewGroup) {
        if (mCurTransaction != null) {
            mCurTransaction?.commitNowAllowingStateLoss()
            mCurTransaction = null
        }
    }

    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return (obj as Fragment).view === view
    }

    override fun saveState(): Parcelable? {
        return null
    }

    override fun restoreState(state: Parcelable?, loader: ClassLoader?) {}

    fun getItemId(position: Int): Long {
        return position.toLong()
    }

    private fun makeFragmentName(viewId: Int, id: Long): String {
        return "android:switcher:$viewId:$id"
    }
}