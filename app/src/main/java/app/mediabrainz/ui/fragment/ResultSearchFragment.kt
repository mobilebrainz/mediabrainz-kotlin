package app.mediabrainz.ui.fragment

import android.os.Bundle
import androidx.lifecycle.Observer
import app.mediabrainz.db.entity.SuggestionField
import app.mediabrainz.domain.OAuthManager.isError
import app.mediabrainz.domain.model.Artist
import app.mediabrainz.domain.model.Recording
import app.mediabrainz.domain.model.ReleaseGroup
import app.mediabrainz.ui.R
import app.mediabrainz.ui.adapter.AlbumSearchAdapter
import app.mediabrainz.ui.adapter.ArtistSearchAdapter
import app.mediabrainz.ui.adapter.RecordingSearchAdapter
import app.mediabrainz.ui.viewmodel.BaseDataSourceViewModel
import app.mediabrainz.ui.viewmodel.dbRepository.SuggestionViewModel
import app.mediabrainz.ui.viewmodel.searchDataSource.PagedArtistSearchViewModel
import app.mediabrainz.ui.viewmodel.searchDataSource.PagedRecordingSearchViewModel
import app.mediabrainz.ui.viewmodel.searchDataSource.PagedReleaseGroupSearchViewModel


class ResultSearchFragment : BaseDataSourceFragment() {

    private var artistQuery: String = ""
    private var albumQuery: String = ""
    private var trackQuery: String = ""
    private var searchQuery: String = ""
    private var searchType = -1

    private lateinit var suggestionViewModel: SuggestionViewModel

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

            suggestionViewModel = getViewModel(SuggestionViewModel::class.java)
            search()
            initSwipeToRefresh()
        }
    }

    private fun search() {
        when {
            searchType != -1 -> {
                setSubtitle(searchQuery)
                /*
                if (searchType == SearchType.TAG.ordinal()) {
                    setTitle(R.string.search_tag_title)
                    resultSearchVM.searchTags(searchQuery, refresh)

                } else if (searchType == SearchType.USER.ordinal()) {
                    setTitle(R.string.search_user_title)
                    resultSearchVM.searchUsers(searchQuery, refresh)

                } else if (searchType == SearchType.BARCODE.ordinal()) {
                    setTitle(R.string.search_barcode_title)
                    resultSearchVM.searchReleasesByBarcode(searchQuery, refresh)
                }
                */
            }
            trackQuery.isNotBlank() -> {
                setTitle(R.string.search_track_title)
                // todo: do title without artistQuery?
                setSubtitle(if (artistQuery.isNotBlank()) "$artistQuery / $trackQuery" else trackQuery)
                //actionBar.setSubtitle(!TextUtils.isEmpty(trackQuery);
                viewModel = initRecordingSearch()
            }
            albumQuery.isNotBlank() -> {
                setTitle(R.string.search_album_title)
                // todo: do title without artistQuery?
                setSubtitle(if (artistQuery.isNotBlank()) "$artistQuery / $albumQuery" else albumQuery)
                //actionBar.setSubtitle(albumQuery);
                viewModel = initAlbumSearch()
            }
            artistQuery.isNotBlank() -> {
                setTitle(R.string.search_artist_title)
                setSubtitle(artistQuery)
                viewModel = initArtistSearch()
            }
        }
    }

    private fun initRecordingSearch(): BaseDataSourceViewModel<*> {
        val vm = getViewModel(PagedRecordingSearchViewModel::class.java)
        vm.search(artistQuery, albumQuery, trackQuery)
        val adapter = RecordingSearchAdapter()
        adapter.holderClickListener = { navigateToRecording(it) }
        vm.pagedItems.observe(this, Observer { adapter.submitList(it) })
        recyclerView.adapter = adapter
        return vm
    }

    private fun initAlbumSearch(): BaseDataSourceViewModel<*> {
        val vm = getViewModel(PagedReleaseGroupSearchViewModel::class.java)
        vm.search(artistQuery, albumQuery)
        val adapter = AlbumSearchAdapter()
        adapter.holderClickListener = { navigateToAlbum(it) }
        vm.pagedItems.observe(this, Observer { adapter.submitList(it) })
        recyclerView.adapter = adapter
        return vm
    }

    private fun initArtistSearch(): BaseDataSourceViewModel<*> {
        val vm = getViewModel(PagedArtistSearchViewModel::class.java)
        vm.search(artistQuery)
        val adapter = ArtistSearchAdapter()
        adapter.holderClickListener = { navigateToArtist(it) }
        vm.pagedItems.observe(this, Observer { adapter.submitList(it) })
        recyclerView.adapter = adapter
        return vm
    }

    private fun navigateToArtist(artist: Artist) {
        if (!isLoading && !isError) {
            suggestionViewModel.insert(artist.name, SuggestionField.ARTIST)
            //val action = NavGraphDirections.actionGlobalArtistFragment(artist.mbid)
            //navigate(action)
        }
    }

    private fun navigateToAlbum(releaseGroup: ReleaseGroup) {
        if (!isLoading && !isError) {
            suggestionViewModel.insert(releaseGroup.name, SuggestionField.ALBUM)
            /*
            val artists = releaseGroup.getArtistCredits()
            if (artists != null && !artists!!.isEmpty()) {
                suggestionViewModel.insert(artists!!.get(0).getArtist().name, SuggestionField.ARTIST)
            }
            val type = ReleaseBrowseService.ReleaseBrowseEntityType.RELEASE_GROUP.ordinal()
            val action = NavGraphDirections.actionGlobalReleasesFragment(type, releaseGroup.mbid, null)
            navigate(action)
            */
        }
    }

    private fun navigateToRecording(recording: Recording) {
        if (!isLoading && !isError) {
            suggestionViewModel.insert(recording.name, SuggestionField.TRACK)
            /*
            val artists = recording.getArtistCredits()
            if (artists != null && !artists!!.isEmpty()) {
                resultSearchVM.insertSuggestion(artists!!.get(0).getArtist().name, SuggestionField.ARTIST)
            }
            navigate(action)
            */
        }
    }

}
