package app.mediabrainz.ui.viewmodel.searchDataSource

import app.mediabrainz.domain.datasource.searchDataSource.SeriesSearchDataSource
import app.mediabrainz.domain.model.Series
import app.mediabrainz.ui.viewmodel.BaseDataSourceViewModel


class PagedSeriesSearchViewModel : BaseDataSourceViewModel<Series>() {

    private val initialLoadSize = 25
    private val loadSize = 25

    fun search(query: String) {
        val factory = SeriesSearchDataSource.Factory(query)
        val config = buildPagedListConfig(loadSize, initialLoadSize)
        initPagedItems(config, factory)
    }

}