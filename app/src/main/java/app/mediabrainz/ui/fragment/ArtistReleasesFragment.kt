package app.mediabrainz.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import app.mediabrainz.ui.R
import app.mediabrainz.ui.adapter.pager.ReleaseGroupsPagerAdapter
import com.google.android.material.tabs.TabLayout


class ArtistReleasesFragment : BaseFragment() {

    private val TAG = "ArtistReleasesF"
    private val RELESES_TAB = "ArtistReleasesFragment.RELESES_TAB"

    private var releaseTab = 0
    private lateinit var pagerView: ViewPager
    private lateinit var tabsView: TabLayout


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflate(R.layout.fragment_pager_without_icons, container)
        savedInstanceState?.let {
            releaseTab = it.getInt(RELESES_TAB, 0)
        }
        pagerView = view.findViewById(R.id.pagerView)
        tabsView = view.findViewById(R.id.tabsView)
        return view
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(RELESES_TAB, releaseTab)
    }

    override fun onPause() {
        super.onPause()
        releaseTab = pagerView.currentItem
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        arguments?.let {
            val args = ArtistReleasesFragmentArgs.fromBundle(it)
            setSubtitle(args.artistName)
            show(args.artistMbid)
        }

    }

    private fun show(artistMbid: String) {
        val pagerAdapter = ReleaseGroupsPagerAdapter(childFragmentManager, resources, artistMbid)
        pagerView.adapter = pagerAdapter
        pagerView.offscreenPageLimit = pagerAdapter.count
        pagerView.currentItem = releaseTab
        tabsView.setupWithViewPager(pagerView)
        pagerAdapter.setupTabViews(tabsView)
    }
}
