package app.mediabrainz.ui.viewmodel.searchDataSource

import app.mediabrainz.domain.datasource.searchDataSource.WorkSearchDataSource
import app.mediabrainz.domain.model.Work
import app.mediabrainz.ui.viewmodel.BaseDataSourceViewModel


class PagedWorkSearchViewModel : BaseDataSourceViewModel<Work>() {

    private val initialLoadSize = 25
    private val loadSize = 25

    fun search(query: String) {
        val factory = WorkSearchDataSource.Factory(query)
        val config = buildPagedListConfig(loadSize, initialLoadSize)
        initPagedItems(config, factory)
    }

}