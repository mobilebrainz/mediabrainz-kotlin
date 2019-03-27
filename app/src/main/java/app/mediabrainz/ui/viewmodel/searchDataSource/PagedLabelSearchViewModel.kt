package app.mediabrainz.ui.viewmodel.searchDataSource

import app.mediabrainz.domain.datasource.searchDataSource.LabelSearchDataSource
import app.mediabrainz.domain.model.Label
import app.mediabrainz.ui.viewmodel.BaseDataSourceViewModel


class PagedLabelSearchViewModel : BaseDataSourceViewModel<Label>() {

    private val initialLoadSize = 25
    private val loadSize = 25

    fun search(query: String) {
        val factory = LabelSearchDataSource.Factory(query)
        val config = buildPagedListConfig(loadSize, initialLoadSize)
        initPagedItems(config, factory)
    }

}