package app.mediabrainz.ui.viewmodel.searchDataSource

import app.mediabrainz.domain.datasource.searchDataSource.EventSearchDataSource
import app.mediabrainz.domain.model.Event
import app.mediabrainz.ui.viewmodel.BaseDataSourceViewModel


class PagedEventSearchViewModel : BaseDataSourceViewModel<Event>() {

    private val initialLoadSize = 25
    private val loadSize = 25

    fun search(query: String) {
        val factory = EventSearchDataSource.Factory(query)
        val config = buildPagedListConfig(loadSize, initialLoadSize)
        initPagedItems(config, factory)
    }

}