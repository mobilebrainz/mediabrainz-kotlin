package app.mediabrainz.ui.viewmodel.searchDataSource

import app.mediabrainz.domain.datasource.searchDataSource.AreaSearchDataSource
import app.mediabrainz.domain.model.Area
import app.mediabrainz.ui.viewmodel.BaseDataSourceViewModel


class PagedAreaSearchViewModel : BaseDataSourceViewModel<Area>() {

    private val initialLoadSize = 25
    private val loadSize = 25

    fun search(query: String) {
        val factory = AreaSearchDataSource.Factory(query)
        val config = buildPagedListConfig(loadSize, initialLoadSize)
        initPagedItems(config, factory)
    }

}