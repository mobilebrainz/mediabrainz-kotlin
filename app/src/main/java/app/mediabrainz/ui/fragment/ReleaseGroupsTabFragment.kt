package app.mediabrainz.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import app.mediabrainz.domain.datasource.browseDataSource.ReleaseGroupsByArtistAndTypeDataSource
import app.mediabrainz.domain.datasource.core.NetworkState
import app.mediabrainz.domain.repository.Resource
import app.mediabrainz.ui.R
import app.mediabrainz.ui.adapter.TestPagedAdapter
import app.mediabrainz.ui.adapter.pager.ReleaseGroupsPagerAdapter
import app.mediabrainz.ui.core.fragment.LazyFragment
import app.mediabrainz.ui.viewmodel.BaseDataSourceViewModel
import app.mediabrainz.ui.viewmodel.browseDataSource.PagedReleaseGroupsByArtistAndTypeViewModel
import app.mediabrainz.ui.viewmodel.searchRepository.ReleaseGroupSearchViewModel


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

    //https://test.musicbrainz.org/ws/2/release-group?query=primarytype:album AND secondarytype:(-*) AND status:official AND arid:5182c1d9-c7d2-4dad-afa0-ccfeada921a8&fmt=json&limit=100&offset=0
    override fun lazyLoad() {
        val rgSearchViewModel: ReleaseGroupSearchViewModel = getViewModel(ReleaseGroupSearchViewModel::class.java)
        rgSearchViewModel.result.observe(this, Observer {
            it?.apply {
                isLoading = status == Resource.Status.LOADING
                swipeRefreshLayout.isRefreshing = isLoading

                isError = status == Resource.Status.ERROR
                showError(true)

                if (status == Resource.Status.SUCCESS) {
                    data?.apply {
                        //Log.i("", "")
                        initDataSource()
                    }
                }
            }
        })
        if (artistMbid != null && releaseGroupType != null) {
            rgSearchViewModel.searchOfficialReleaseGroups(artistMbid!!, releaseGroupType!!.type, 0, 100)
        }

    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        showError(isVisibleToUser)
    }

    private fun showError(isVisibleToUser: Boolean) {
        if (isVisibleToUser && isError) {
            showErrorSnackbar(R.string.connection_error, R.string.connection_error_retry, View.OnClickListener { lazyLoad() })
        } else {
            dismissErrorSnackbar()
        }
    }

    ///////////////////////////

    protected lateinit var viewModel: BaseDataSourceViewModel<*>

    private fun initDataSource() {
        val vm = getViewModel(PagedReleaseGroupsByArtistAndTypeViewModel::class.java)
        vm.browse(artistMbid!!, releaseGroupType!!.type, false)
        viewModel = vm

        val adapter = TestPagedAdapter()
        vm.pagedItems.observe(this, Observer { adapter.submitList(it) })

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.isNestedScrollingEnabled = true
        //recyclerView.setItemViewCacheSize(10)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter

        initSwipeToRefresh()
    }

    protected fun initSwipeToRefresh() {
        viewModel.getInitialLoadState().observe(this, Observer {
            isLoading = it.status == NetworkState.Status.LOADING
            swipeRefreshLayout.isRefreshing = isLoading
            //showError(it.status == NetworkState.Status.ERROR, it.messageResId)
        })

        viewModel.getAfterLoadState().observe(this, Observer {
            isLoading = it.status == NetworkState.Status.LOADING
            swipeRefreshLayout.isRefreshing = isLoading
            //showError(it.status == NetworkState.Status.ERROR, it.messageResId)
        })

        swipeRefreshLayout.setOnRefreshListener {
            if (!isLoading) {
                viewModel.refresh()
                recyclerView.scrollToPosition(0)
            }
        }
    }

}
