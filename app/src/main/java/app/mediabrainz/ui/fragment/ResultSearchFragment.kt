package app.mediabrainz.ui.fragment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import app.mediabrainz.ui.R
import app.mediabrainz.ui.adapter.AlbumSearchAdapter
import app.mediabrainz.ui.adapter.ArtistSearchAdapter
import app.mediabrainz.ui.adapter.RecordingSearchAdapter
import app.mediabrainz.ui.viewmodel.BaseDataSourceViewModel
import app.mediabrainz.ui.viewmodel.searchDataSource.PagedArtistSearchViewModel
import app.mediabrainz.ui.viewmodel.searchDataSource.PagedRecordingSearchViewModel
import app.mediabrainz.ui.viewmodel.searchDataSource.PagedReleaseGroupSearchViewModel


class ResultSearchFragment : BaseDataSourceFragment() {

    private var artistQuery: String = ""
    private var albumQuery: String = ""
    private var trackQuery: String = ""
    private var searchQuery: String = ""
    private var searchType = -1

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
            initSwipeToRefresh()
        }
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
        recyclerView.adapter = adapter
        return vm
    }

    private fun initAlbumSearch(): BaseDataSourceViewModel<*> {
        val vm = getViewModel(PagedReleaseGroupSearchViewModel::class.java)
        vm.search(artistQuery, albumQuery)
        val adapter = AlbumSearchAdapter()
        vm.pagedItems.observe(this, Observer { adapter.submitList(it) })
        recyclerView.adapter = adapter
        return vm
    }

    private fun initArtistSearch(): BaseDataSourceViewModel<*> {
        val vm = getViewModel(PagedArtistSearchViewModel::class.java)
        vm.search(artistQuery)
        val adapter = ArtistSearchAdapter()
        vm.pagedItems.observe(this, Observer { adapter.submitList(it) })
        recyclerView.adapter = adapter
        return vm
    }

}
