package app.mediabrainz.ui.core.adapter

import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout


interface FragmentPagerAdapterInterface {

    fun setupTabViews(tabLayout: TabLayout)

    fun getFragment(position: Int): Fragment?

    fun updateFragments()
}