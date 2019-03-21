package app.mediabrainz.ui.viewmodel.datasource

import app.mediabrainz.domain.datasource.CDStubSearchDataSource
import app.mediabrainz.domain.model.CDStub


class PagedCDStubSearchViewModel : BaseDataSourceViewModel<CDStub>() {

    private val initialLoadSize = 25
    private val loadSize = 25

    fun search(query: String) {
        val factory = CDStubSearchDataSource.Factory(query)
        val config = buildPagedListConfig(loadSize, initialLoadSize)
        initPagedItems(config, factory)
    }

}