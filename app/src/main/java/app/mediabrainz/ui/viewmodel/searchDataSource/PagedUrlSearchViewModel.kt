package app.mediabrainz.ui.viewmodel.searchDataSource

import app.mediabrainz.domain.datasource.searchDataSource.UrlSearchDataSource
import app.mediabrainz.domain.model.Url
import app.mediabrainz.ui.viewmodel.BaseDataSourceViewModel


class PagedUrlSearchViewModel : BaseDataSourceViewModel<Url>() {

    private val initialLoadSize = 25
    private val loadSize = 25

    fun search(query: String) {
        val factory = UrlSearchDataSource.Factory(query)
        val config = buildPagedListConfig(loadSize, initialLoadSize)
        initPagedItems(config, factory)
    }

}