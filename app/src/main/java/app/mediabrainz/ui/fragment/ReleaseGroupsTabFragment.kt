package app.mediabrainz.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import app.mediabrainz.ui.R
import app.mediabrainz.ui.adapter.pager.ReleaseGroupsPagerAdapter
import app.mediabrainz.ui.core.fragment.LazyFragment


class ReleaseGroupsTabFragment : LazyFragment() {

    companion object {
        private const val RELEASES_TAB = "ReleaseGroupsTabFragment.RELEASES_TAB"
        private const val ARTIST_MBID = "ReleaseGroupsTabFragment.ARTIST_MBID"

        fun newInstance(releasesTab: Int, artistMbid: String): ReleaseGroupsTabFragment {
            val args = Bundle()
            args.putInt(RELEASES_TAB, releasesTab)
            args.putString(ARTIST_MBID, artistMbid)
            val fragment = ReleaseGroupsTabFragment()
            fragment.arguments = args
            return fragment
        }
    }

    val TAG = "ReleaseGroupsTabF"

    private var isError: Boolean = false
    private var isLoading: Boolean = false
    private var artistMbid: String? = null
    private var releaseGroupType: ReleaseGroupsPagerAdapter.ReleaseTab? = null

    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val layout = inflate(R.layout.paged_recycler_fragment, container)

        recyclerView = layout.findViewById(R.id.recyclerView)
        swipeRefreshLayout = layout.findViewById(R.id.swipeRefreshLayout)

        return layout
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        arguments?.let {
            artistMbid = it.getString(ARTIST_MBID)
            releaseGroupType = ReleaseGroupsPagerAdapter.ReleaseTab.values()[it.getInt(RELEASES_TAB)]
            loadView()
        }
    }

    override fun lazyLoad() {

    }

}
