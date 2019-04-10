package app.mediabrainz.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import app.mediabrainz.domain.datasource.core.NetworkState.Status.ERROR
import app.mediabrainz.domain.datasource.core.NetworkState.Status.LOADING
import app.mediabrainz.ui.R
import app.mediabrainz.ui.adapter.AlbumSearchAdapter
import app.mediabrainz.ui.adapter.ArtistSearchAdapter
import app.mediabrainz.ui.adapter.RecordingSearchAdapter
import app.mediabrainz.ui.viewmodel.BaseDataSourceViewModel
import app.mediabrainz.ui.viewmodel.searchDataSource.PagedArtistSearchViewModel
import app.mediabrainz.ui.viewmodel.searchDataSource.PagedRecordingSearchViewModel
import app.mediabrainz.ui.viewmodel.searchDataSource.PagedReleaseGroupSearchViewModel


class ResultSearchFragment2 : BaseFragment() {

    private var artistQuery: String = ""
    private var albumQuery: String = ""
    private var trackQuery: String = ""
    private var searchQuery: String = ""
    private var searchType = -1
    private var isError: Boolean = false
    private var isLoading: Boolean = false

    private lateinit var recyclerView: RecyclerView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var viewModel: BaseDataSourceViewModel<*>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.paged_recycler_fragment, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        arguments?.let {
            setSubtitle("")

            val args = ResultSearchFragmentArgs.fromBundle(it)
            artistQuery = args.artistQuery
            albumQuery = args.albumQuery
            trackQuery = args.trackQuery
            searchQuery = args.searchQuery
            searchType = args.searchType

            search()
            initRecycler()
            initSwipeToRefresh()
        }
    }

    private fun initRecycler() {
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.isNestedScrollingEnabled = true
        //recyclerView.setItemViewCacheSize(10)
        recyclerView.setHasFixedSize(true)
    }

    private fun initSwipeToRefresh() {
        viewModel.getInitialLoadState().observe(this, Observer {
            isLoading = it.status == LOADING
            swipeRefreshLayout.isRefreshing = isLoading
            showError(it.status == ERROR, it.messageResId)
        })

        viewModel.getAfterLoadState().observe(this, Observer {
            isLoading = it.status == LOADING
            swipeRefreshLayout.isRefreshing = isLoading
            showError(it.status == ERROR, it.messageResId)
        })

        swipeRefreshLayout.setOnRefreshListener {
            if (!isLoading) {
                viewModel.refresh()
                recyclerView.scrollToPosition(0)
            }
        }
    }

    private fun showError(show: Boolean, @StringRes messageResId: Int) {
        if (show) {
            showErrorSnackbar(messageResId, R.string.retry, View.OnClickListener { retry() })
        } else {
            dismissErrorSnackbar()
        }
    }

    private fun retry() {
        viewModel.retry()
    }

    private fun search() {
        if (activity == null) return
        val actionBar = (activity as AppCompatActivity).supportActionBar ?: return

        when {
            searchType != -1 -> {
                actionBar.subtitle = searchQuery
                /*
                if (searchType == SearchType.TAG.ordinal()) {
                    actionBar.setTitle(getString(R.string.search_tag_title))
                    resultSearchVM.searchTags(searchQuery, refresh)

                } else if (searchType == SearchType.USER.ordinal()) {
                    actionBar.setTitle(getString(R.string.search_user_title))
                    resultSearchVM.searchUsers(searchQuery, refresh)

                } else if (searchType == SearchType.BARCODE.ordinal()) {
                    actionBar.setTitle(getString(R.string.search_barcode_title))
                    resultSearchVM.searchReleasesByBarcode(searchQuery, refresh)
                }
                */
            }
            trackQuery.isNotBlank() -> {
                actionBar.setTitle(R.string.search_track_title)
                // todo: do title without artistQuery?
                actionBar.subtitle =
                    if (artistQuery.isNotBlank()) "$artistQuery / $trackQuery" else trackQuery
                //actionBar.setSubtitle(!TextUtils.isEmpty(trackQuery);
                viewModel = initRecordingSearch()
            }
            albumQuery.isNotBlank() -> {
                actionBar.setTitle(R.string.search_album_title)
                // todo: do title without artistQuery?
                actionBar.subtitle =
                    if (artistQuery.isNotBlank()) "$artistQuery / $albumQuery" else albumQuery
                //actionBar.setSubtitle(albumQuery);
                viewModel = initAlbumSearch()
            }
            artistQuery.isNotBlank() -> {
                actionBar.setTitle(R.string.search_artist_title)
                actionBar.subtitle = artistQuery
                viewModel = initArtistSearch()
            }
        }
    }

    private fun initRecordingSearch(): BaseDataSourceViewModel<*> {
        val vm = getViewModel(PagedRecordingSearchViewModel::class.java)
        vm.search(artistQuery, albumQuery, trackQuery)
        val adapter = RecordingSearchAdapter()
        vm.pagedItems.observe(this, Observer { adapter.submitList(it) })
        recyclerView.adapter  = adapter
        return vm
    }

    private fun initAlbumSearch(): BaseDataSourceViewModel<*> {
        val vm = getViewModel(PagedReleaseGroupSearchViewModel::class.java)
        vm.search(artistQuery, albumQuery)
        val adapter = AlbumSearchAdapter()
        vm.pagedItems.observe(this, Observer { adapter.submitList(it) })
        recyclerView.adapter  = adapter
        return vm
    }

    private fun initArtistSearch(): BaseDataSourceViewModel<*> {
        val vm = getViewModel(PagedArtistSearchViewModel::class.java)
        vm.search(artistQuery)
        val adapter = ArtistSearchAdapter()
        vm.pagedItems.observe(this, Observer { adapter.submitList(it) })
        recyclerView.adapter  = adapter
        return vm
    }

}
