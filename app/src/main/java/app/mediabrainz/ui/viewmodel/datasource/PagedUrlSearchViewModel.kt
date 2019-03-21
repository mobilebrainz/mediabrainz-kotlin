package app.mediabrainz.ui.viewmodel.datasource

import app.mediabrainz.domain.datasource.UrlSearchDataSource
import app.mediabrainz.domain.model.Url


class PagedUrlSearchViewModel : BaseDataSourceViewModel<Url>() {

    private val initialLoadSize = 25
    private val loadSize = 25

    fun search(query: String) {
        val factory = UrlSearchDataSource.Factory(query)
        val config = buildPagedListConfig(loadSize, initialLoadSize)
        initPagedItems(config, factory)
    }

}