package app.mediabrainz.ui.viewmodel.searchDataSource

import app.mediabrainz.domain.datasource.searchDataSource.PlaceSearchDataSource
import app.mediabrainz.domain.model.Place
import app.mediabrainz.ui.viewmodel.BaseDataSourceViewModel


class PagedPlaceSearchViewModel : BaseDataSourceViewModel<Place>() {

    private val initialLoadSize = 25
    private val loadSize = 25

    fun search(query: String) {
        val factory = PlaceSearchDataSource.Factory(query)
        val config = buildPagedListConfig(loadSize, initialLoadSize)
        initPagedItems(config, factory)
    }

}