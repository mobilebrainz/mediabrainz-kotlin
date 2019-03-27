package app.mediabrainz.ui.viewmodel.searchDataSource

import app.mediabrainz.domain.datasource.searchDataSource.CDStubSearchDataSource
import app.mediabrainz.domain.model.CDStub
import app.mediabrainz.ui.viewmodel.BaseDataSourceViewModel


class PagedCDStubSearchViewModel : BaseDataSourceViewModel<CDStub>() {

    private val initialLoadSize = 25
    private val loadSize = 25

    fun search(query: String) {
        val factory = CDStubSearchDataSource.Factory(query)
        val config = buildPagedListConfig(loadSize, initialLoadSize)
        initPagedItems(config, factory)
    }

}