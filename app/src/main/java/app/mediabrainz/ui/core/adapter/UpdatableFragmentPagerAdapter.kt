package app.mediabrainz.ui.core.adapter

import android.content.res.Resources
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.tabs.TabLayout
import java.util.*


abstract class UpdatableFragmentPagerAdapter(
    val pageCount: Int,
    val fragmentManager: FragmentManager,
    val resources: Resources
) : BaseFragmentPagerAdapter(fragmentManager), FragmentPagerAdapterInterface {

    interface Updatable {
        fun update()
    }

    protected val tabTitles: IntArray = IntArray(pageCount)
    protected val tabIcons: IntArray = IntArray(pageCount)
    private val fragmentTags = HashMap<Int, String>()

    override fun getCount(): Int {
        return pageCount
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return if (tabTitles[position] != 0) resources.getString(tabTitles[position]) else null
    }

    override fun setupTabViews(tabLayout: TabLayout) {
        for (i in 0 until pageCount) {
            tabLayout.getTabAt(i)?.let {
                if (tabIcons[i] != 0) {
                    it.setIcon(tabIcons[i])
                }
                if (tabTitles[i] != 0) {
                    it.setText(tabTitles[i])
                }
            }
        }
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val obj = super.instantiateItem(container, position)
        if (obj is Fragment) {
            obj.tag?.let {
                fragmentTags[position] = it
            }
        }
        return obj
    }

    override fun getFragment(position: Int): Fragment? {
        val tag = fragmentTags[position]
        return if (tag != null) fragmentManager.findFragmentByTag(tag) else null
    }

    override fun updateFragments() {
        for (i in 0 until pageCount) {
            val fragment = getFragment(i)
            if (fragment is Updatable) {
                (fragment as Updatable).update()
            }
        }
    }
}