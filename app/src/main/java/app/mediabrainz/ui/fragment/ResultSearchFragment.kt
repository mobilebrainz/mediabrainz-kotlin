package app.mediabrainz.ui.fragment

import android.os.Bundle
import androidx.lifecycle.Observer
import app.mediabrainz.db.entity.SuggestionField
import app.mediabrainz.domain.OAuthManager.isError
import app.mediabrainz.domain.model.*
import app.mediabrainz.domain.model.Annotation
import app.mediabrainz.ui.R
import app.mediabrainz.ui.adapter.AlbumSearchAdapter
import app.mediabrainz.ui.adapter.AreaSearchAdapter
import app.mediabrainz.ui.adapter.ArtistSearchAdapter
import app.mediabrainz.ui.adapter.RecordingSearchAdapter
import app.mediabrainz.ui.fragment.SearchType.*
import app.mediabrainz.ui.viewmodel.BaseDataSourceViewModel
import app.mediabrainz.ui.viewmodel.dbRepository.SuggestionViewModel
import app.mediabrainz.ui.viewmodel.searchDataSource.PagedAreaSearchViewModel
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
            if (search()) {
                initSwipeToRefresh()
            }
        }
    }

    private fun search() : Boolean {
        var flag = true
        when {
            searchType != -1 -> {
                setSubtitle(searchQuery)
                when (searchType) {
                    ANNOTATION.ordinal -> {
                        setTitle(R.string.search_annotation_title)
                        //viewModel = initAnnotationSearch()
                        //todo: for test
                        flag = false
                    }
                    AREA.ordinal -> {
                        setTitle(R.string.search_area_title)
                        viewModel = initAreaSearch()
                    }
                    BARCODE.ordinal -> {
                        setTitle(R.string.search_barcode_title)
                        //viewModel = initBarcodeSearch()
                        //todo: for test
                        flag = false
                    }
                    CDSTUB.ordinal -> {
                        setTitle(R.string.search_cdstub_title)
                        //viewModel = initCdstubSearch()
                        //todo: for test
                        flag = false
                    }
                    EVENT.ordinal -> {
                        setTitle(R.string.search_event_title)
                        //viewModel = initEventSearch()
                        //todo: for test
                        flag = false
                    }
                    INSTRUMENT.ordinal -> {
                        setTitle(R.string.search_instrument_title)
                        //viewModel = initInstrumentSearch()
                        //todo: for test
                        flag = false
                    }
                    LABEL.ordinal -> {
                        setTitle(R.string.search_label_title)
                        //viewModel = initLabelSearch()
                        //todo: for test
                        flag = false
                    }
                    PLACE.ordinal -> {
                        setTitle(R.string.search_place_title)
                        //viewModel = initPlaceSearch()
                        //todo: for test
                        flag = false
                    }
                    RELEASE.ordinal -> {
                        setTitle(R.string.search_release_title)
                        //viewModel = initReleaseSearch()
                        //todo: for test
                        flag = false
                    }
                    SERIES.ordinal -> {
                        setTitle(R.string.search_series_title)
                        //viewModel = initSeriesSearch()
                        //todo: for test
                        flag = false
                    }
                    TAG.ordinal -> {
                        setTitle(R.string.search_tag_title)
                        //viewModel = initTagSearch()
                        //todo: for test
                        flag = false
                    }
                    URL.ordinal -> {
                        setTitle(R.string.search_url_title)
                        //viewModel = initUrlSearch()
                        //todo: for test
                        flag = false
                    }
                    WORK.ordinal -> {
                        setTitle(R.string.search_work_title)
                        //viewModel = initWorkSearch()
                        //todo: for test
                        flag = false
                    }
                    USER.ordinal -> {
                        setTitle(R.string.search_user_title)
                        //viewModel = initUserSearch()
                        //todo: for test
                        flag = false
                    }
                }
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
            else -> flag = false
        }
        return flag
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

    private fun initAreaSearch(): BaseDataSourceViewModel<*> {
        val vm = getViewModel(PagedAreaSearchViewModel::class.java)
        vm.search(searchQuery)
        val adapter = AreaSearchAdapter()
        adapter.holderClickListener = { navigateToArea(it) }
        vm.pagedItems.observe(this, Observer { adapter.submitList(it) })
        recyclerView.adapter = adapter
        return vm
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

    private fun navigateToAnnotation(item: Annotation) {
        if (!isLoading && !isError) {
            suggestionViewModel.insert(item.name, SuggestionField.ANNOTATION)
            //val action = NavGraphDirections.
            //navigate(action)
        }
    }

    private fun navigateToArea(item: Area) {
        if (!isLoading && !isError) {
            suggestionViewModel.insert(item.name, SuggestionField.AREA)
            //val action = NavGraphDirections.
            //navigate(action)
        }
    }

    /*
    private fun navigateToBarcode(item: Barcode) {
        if (!isLoading && !isError) {
            suggestionViewModel.insert(item.name, SuggestionField.BARCODE)
            //val action = NavGraphDirections.
            //navigate(action)
        }
    }
    */

    private fun navigateToCDStub(item: CDStub) {
        if (!isLoading && !isError) {
            suggestionViewModel.insert(item.name, SuggestionField.CDSTUB)
            //val action = NavGraphDirections.
            //navigate(action)
        }
    }

    private fun navigateToEvent(item: Event) {
        if (!isLoading && !isError) {
            suggestionViewModel.insert(item.name, SuggestionField.EVENT)
            //val action = NavGraphDirections.
            //navigate(action)
        }
    }

    private fun navigateToInstrument(item: Instrument) {
        if (!isLoading && !isError) {
            suggestionViewModel.insert(item.name, SuggestionField.INSTRUMENT)
            //val action = NavGraphDirections.
            //navigate(action)
        }
    }

    private fun navigateToLabel(item: Label) {
        if (!isLoading && !isError) {
            suggestionViewModel.insert(item.name, SuggestionField.LABEL)
            //val action = NavGraphDirections.
            //navigate(action)
        }
    }

    private fun navigateToPlace(item: Place) {
        if (!isLoading && !isError) {
            suggestionViewModel.insert(item.name, SuggestionField.PLACE)
            //val action = NavGraphDirections.
            //navigate(action)
        }
    }

    private fun navigateToRelease(item: Release) {
        if (!isLoading && !isError) {
            suggestionViewModel.insert(item.name, SuggestionField.RELEASE)
            //val action = NavGraphDirections.
            //navigate(action)
        }
    }

    private fun navigateToSeries(item: Series) {
        if (!isLoading && !isError) {
            suggestionViewModel.insert(item.name, SuggestionField.SERIES)
            //val action = NavGraphDirections.
            //navigate(action)
        }
    }

    private fun navigateToTag(item: Tag) {
        if (!isLoading && !isError) {
            suggestionViewModel.insert(item.name, SuggestionField.TAG)
            //val action = NavGraphDirections.
            //navigate(action)
        }
    }

    /*
    private fun navigateToUrl(item: Url) {
        if (!isLoading && !isError) {
            suggestionViewModel.insert(item.name, SuggestionField.URL)
            //val action = NavGraphDirections.
            //navigate(action)
        }
    }
    */

    private fun navigateToWork(item: Work) {
        if (!isLoading && !isError) {
            suggestionViewModel.insert(item.name, SuggestionField.WORK)
            //val action = NavGraphDirections.
            //navigate(action)
        }
    }

}
