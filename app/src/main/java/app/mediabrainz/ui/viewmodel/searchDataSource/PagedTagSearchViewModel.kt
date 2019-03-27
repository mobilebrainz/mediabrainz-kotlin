package app.mediabrainz.ui.viewmodel.searchDataSource

import app.mediabrainz.domain.datasource.searchDataSource.TagSearchDataSource
import app.mediabrainz.domain.model.Tag
import app.mediabrainz.ui.viewmodel.BaseDataSourceViewModel


class PagedTagSearchViewModel : BaseDataSourceViewModel<Tag>() {

    private val initialLoadSize = 25
    private val loadSize = 25

    fun search(query: String) {
        val factory = TagSearchDataSource.Factory(query)
        val config = buildPagedListConfig(loadSize, initialLoadSize)
        initPagedItems(config, factory)
    }

}