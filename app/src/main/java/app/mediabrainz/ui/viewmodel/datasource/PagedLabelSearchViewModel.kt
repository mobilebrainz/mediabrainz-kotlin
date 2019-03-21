package app.mediabrainz.ui.viewmodel.datasource

import app.mediabrainz.domain.datasource.LabelSearchDataSource
import app.mediabrainz.domain.model.Label


class PagedLabelSearchViewModel : BaseDataSourceViewModel<Label>() {

    private val initialLoadSize = 25
    private val loadSize = 25

    fun search(query: String) {
        val factory = LabelSearchDataSource.Factory(query)
        val config = buildPagedListConfig(loadSize, initialLoadSize)
        initPagedItems(config, factory)
    }

}