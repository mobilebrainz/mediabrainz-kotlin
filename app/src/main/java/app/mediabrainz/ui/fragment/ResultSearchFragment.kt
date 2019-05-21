package app.mediabrainz.ui.fragment

import android.os.Bundle
import androidx.lifecycle.Observer
import app.mediabrainz.api.browserequest.ReleaseBrowseEntityType
import app.mediabrainz.db.entity.SuggestionField
import app.mediabrainz.domain.OAuthManager.isError
import app.mediabrainz.ui.NavGraphDirections
import app.mediabrainz.ui.R
import app.mediabrainz.ui.adapter.*
import app.mediabrainz.ui.fragment.SearchType.*
import app.mediabrainz.ui.viewmodel.BaseDataSourceViewModel
import app.mediabrainz.ui.viewmodel.dbRepository.SuggestionViewModel
import app.mediabrainz.ui.viewmodel.searchDataSource.*


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

    private fun search(): Boolean {
        var flag = true
        when {
            searchType != -1 -> {
                setSubtitle(searchQuery)
                when (searchType) {
                    ANNOTATION.ordinal -> {
                        setTitle(R.string.search_annotation_title)
                        viewModel = initAnnotationSearch()
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
                        viewModel = initCDStubSearch()
                    }
                    EVENT.ordinal -> {
                        setTitle(R.string.search_event_title)
                        viewModel = initEventSearch()
                    }
                    INSTRUMENT.ordinal -> {
                        setTitle(R.string.search_instrument_title)
                        viewModel = initInstrumentSearch()
                    }
                    LABEL.ordinal -> {
                        setTitle(R.string.search_label_title)
                        viewModel = initLabelSearch()
                    }
                    PLACE.ordinal -> {
                        setTitle(R.string.search_place_title)
                        viewModel = initPlaceSearch()
                    }
                    RELEASE.ordinal -> {
                        setTitle(R.string.search_release_title)
                        viewModel = initReleaseSearch()
                    }
                    SERIES.ordinal -> {
                        setTitle(R.string.search_series_title)
                        viewModel = initSeriesSearch()
                    }
                    TAG.ordinal -> {
                        setTitle(R.string.search_tag_title)
                        viewModel = initTagSearch()
                    }
                    URL.ordinal -> {
                        setTitle(R.string.search_url_title)
                        viewModel = initUrlSearch()
                    }
                    WORK.ordinal -> {
                        setTitle(R.string.search_work_title)
                        viewModel = initWorkSearch()
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
                viewModel = initReleaseGroupSearch()
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
        adapter.holderClickListener = {
            if (!isLoading && !isError) {
                suggestionViewModel.insert(it.name, SuggestionField.RECORDING)
                /*
                val artists = recording.getArtistCredits()
                if (artists != null && !artists!!.isEmpty()) {
                    resultSearchVM.insertSuggestion(artists!!.get(0).getArtist().name, SuggestionField.ARTIST)
                }
                navigate(action)
                */
            }
        }
        vm.pagedItems.observe(this, Observer { adapter.submitList(it) })
        recyclerView.adapter = adapter
        return vm
    }

    private fun initReleaseGroupSearch(): BaseDataSourceViewModel<*> {
        val vm = getViewModel(PagedReleaseGroupSearchViewModel::class.java)
        vm.search(artistQuery, albumQuery)
        val adapter = ReleaseGroupSearchAdapter(this)
        adapter.holderClickListener = {
            if (!isLoading && !isError) {
                suggestionViewModel.insert(it.name, SuggestionField.RELEASE_GROUP)

                val artists = it.artistCredits
                if (artists.isNotEmpty()) {
                    suggestionViewModel.insert(artists[0].artist.name, SuggestionField.ARTIST)
                }

                val type = ReleaseBrowseEntityType.RELEASE_GROUP.ordinal
                val action = NavGraphDirections.actionGlobalReleasesFragment(type, it.mbid, null)
                navigate(action)
            }
        }
        vm.pagedItems.observe(this, Observer { adapter.submitList(it) })
        recyclerView.adapter = adapter
        return vm
    }

    private fun initArtistSearch(): BaseDataSourceViewModel<*> {
        val vm = getViewModel(PagedArtistSearchViewModel::class.java)
        vm.search(artistQuery)
        val adapter = ArtistSearchAdapter(this)
        adapter.holderClickListener = {
            if (!isLoading && !isError) {
                suggestionViewModel.insert(it.name, SuggestionField.ARTIST)
                navigate(NavGraphDirections.actionGlobalArtistFragment(it.mbid))
            }
        }
        vm.pagedItems.observe(this, Observer { adapter.submitList(it) })
        recyclerView.adapter = adapter
        return vm
    }

    private fun initAreaSearch(): BaseDataSourceViewModel<*> {
        val vm = getViewModel(PagedAreaSearchViewModel::class.java)
        vm.search(searchQuery)
        val adapter = AreaSearchAdapter()
        adapter.holderClickListener = {
            if (!isLoading && !isError) {
                suggestionViewModel.insert(it.name, SuggestionField.AREA)
                //val action = NavGraphDirections.
                //navigate(action)
            }
        }
        vm.pagedItems.observe(this, Observer { adapter.submitList(it) })
        recyclerView.adapter = adapter
        return vm
    }

    private fun initAnnotationSearch(): BaseDataSourceViewModel<*> {
        val vm = getViewModel(PagedAnnotationSearchViewModel::class.java)
        vm.search(searchQuery)
        val adapter = AnnotationSearchAdapter()
        adapter.holderClickListener = {
            if (!isLoading && !isError) {
                suggestionViewModel.insert(it.name, SuggestionField.ANNOTATION)
                //val action = NavGraphDirections.
                //navigate(action)
            }
        }
        vm.pagedItems.observe(this, Observer { adapter.submitList(it) })
        recyclerView.adapter = adapter
        return vm
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

    private fun initCDStubSearch(): BaseDataSourceViewModel<*> {
        val vm = getViewModel(PagedCDStubSearchViewModel::class.java)
        vm.search(searchQuery)
        val adapter = CDStubSearchAdapter()
        adapter.holderClickListener = {
            if (!isLoading && !isError) {
                suggestionViewModel.insert(it.name, SuggestionField.CDSTUB)
                //val action = NavGraphDirections.
                //navigate(action)
            }
        }
        vm.pagedItems.observe(this, Observer { adapter.submitList(it) })
        recyclerView.adapter = adapter
        return vm
    }

    private fun initEventSearch(): BaseDataSourceViewModel<*> {
        val vm = getViewModel(PagedEventSearchViewModel::class.java)
        vm.search(searchQuery)
        val adapter = EventSearchAdapter()
        adapter.holderClickListener = {
            if (!isLoading && !isError) {
                suggestionViewModel.insert(it.name, SuggestionField.EVENT)
                //val action = NavGraphDirections.
                //navigate(action)
            }
        }
        vm.pagedItems.observe(this, Observer { adapter.submitList(it) })
        recyclerView.adapter = adapter
        return vm
    }

    private fun initInstrumentSearch(): BaseDataSourceViewModel<*> {
        val vm = getViewModel(PagedInstrumentSearchViewModel::class.java)
        vm.search(searchQuery)
        val adapter = InstrumentSearchAdapter()
        adapter.holderClickListener = {
            if (!isLoading && !isError) {
                suggestionViewModel.insert(it.name, SuggestionField.INSTRUMENT)
                //val action = NavGraphDirections.
                //navigate(action)
            }
        }
        vm.pagedItems.observe(this, Observer { adapter.submitList(it) })
        recyclerView.adapter = adapter
        return vm
    }

    private fun initLabelSearch(): BaseDataSourceViewModel<*> {
        val vm = getViewModel(PagedLabelSearchViewModel::class.java)
        vm.search(searchQuery)
        val adapter = LabelSearchAdapter()
        adapter.holderClickListener = {
            if (!isLoading && !isError) {
                suggestionViewModel.insert(it.name, SuggestionField.LABEL)
                //val action = NavGraphDirections.
                //navigate(action)
            }
        }
        vm.pagedItems.observe(this, Observer { adapter.submitList(it) })
        recyclerView.adapter = adapter
        return vm
    }

    private fun initPlaceSearch(): BaseDataSourceViewModel<*> {
        val vm = getViewModel(PagedPlaceSearchViewModel::class.java)
        vm.search(searchQuery)
        val adapter = PlaceSearchAdapter()
        adapter.holderClickListener = {
            if (!isLoading && !isError) {
                suggestionViewModel.insert(it.name, SuggestionField.PLACE)
                //val action = NavGraphDirections.
                //navigate(action)
            }
        }
        vm.pagedItems.observe(this, Observer { adapter.submitList(it) })
        recyclerView.adapter = adapter
        return vm
    }

    private fun initReleaseSearch(): BaseDataSourceViewModel<*> {
        val vm = getViewModel(PagedReleaseSearchViewModel::class.java)
        vm.search(searchQuery)
        val adapter = ReleaseSearchAdapter()
        adapter.holderClickListener = {
            if (!isLoading && !isError) {
                suggestionViewModel.insert(it.name, SuggestionField.RELEASE)
                //val action = NavGraphDirections.
                //navigate(action)
            }
        }
        vm.pagedItems.observe(this, Observer { adapter.submitList(it) })
        recyclerView.adapter = adapter
        return vm
    }

    private fun initSeriesSearch(): BaseDataSourceViewModel<*> {
        val vm = getViewModel(PagedSeriesSearchViewModel::class.java)
        vm.search(searchQuery)
        val adapter = SeriesSearchAdapter()
        adapter.holderClickListener = {
            if (!isLoading && !isError) {
                suggestionViewModel.insert(it.name, SuggestionField.SERIES)
                //val action = NavGraphDirections.
                //navigate(action)
            }
        }
        vm.pagedItems.observe(this, Observer { adapter.submitList(it) })
        recyclerView.adapter = adapter
        return vm
    }

    private fun initTagSearch(): BaseDataSourceViewModel<*> {
        val vm = getViewModel(PagedTagSearchViewModel::class.java)
        vm.search(searchQuery)
        val adapter = TagSearchAdapter()
        adapter.holderClickListener = {
            if (!isLoading && !isError) {
                suggestionViewModel.insert(it.name, SuggestionField.TAG)
                //val action = NavGraphDirections.
                //navigate(action)
            }
        }
        vm.pagedItems.observe(this, Observer { adapter.submitList(it) })
        recyclerView.adapter = adapter
        return vm
    }

    private fun initUrlSearch(): BaseDataSourceViewModel<*> {
        val vm = getViewModel(PagedUrlSearchViewModel::class.java)
        vm.search(searchQuery)
        val adapter = UrlSearchAdapter()
        adapter.holderClickListener = {
            if (!isLoading && !isError) {
                suggestionViewModel.insert(it.resource, SuggestionField.URL)
                //val action = NavGraphDirections.
                //navigate(action)
            }
        }
        vm.pagedItems.observe(this, Observer { adapter.submitList(it) })
        recyclerView.adapter = adapter
        return vm
    }

    private fun initWorkSearch(): BaseDataSourceViewModel<*> {
        val vm = getViewModel(PagedWorkSearchViewModel::class.java)
        vm.search(searchQuery)
        val adapter = WorkSearchAdapter()
        adapter.holderClickListener = {
            if (!isLoading && !isError) {
                suggestionViewModel.insert(it.name, SuggestionField.WORK)
                //val action = NavGraphDirections.
                //navigate(action)
            }
        }
        vm.pagedItems.observe(this, Observer { adapter.submitList(it) })
        recyclerView.adapter = adapter
        return vm
    }

}
