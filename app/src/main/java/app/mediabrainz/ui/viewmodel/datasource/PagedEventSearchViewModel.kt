package app.mediabrainz.ui.viewmodel.datasource

import app.mediabrainz.domain.datasource.EventSearchDataSource
import app.mediabrainz.domain.model.Event


class PagedEventSearchViewModel : BaseDataSourceViewModel<Event>() {

    private val initialLoadSize = 25
    private val loadSize = 25

    fun search(query: String) {
        val factory = EventSearchDataSource.Factory(query)
        val config = buildPagedListConfig(loadSize, initialLoadSize)
        initPagedItems(config, factory)
    }

}