package app.mediabrainz.ui.viewmodel.searchDataSource

import app.mediabrainz.domain.datasource.searchDataSource.AnnotationSearchDataSource
import app.mediabrainz.domain.model.Annotation
import app.mediabrainz.ui.viewmodel.BaseDataSourceViewModel


class PagedAnnotationSearchViewModel : BaseDataSourceViewModel<Annotation>() {

    private val initialLoadSize = 25
    private val loadSize = 25

    fun search(query: String) {
        val factory = AnnotationSearchDataSource.Factory(query)
        val config = buildPagedListConfig(loadSize, initialLoadSize)
        initPagedItems(config, factory)
    }

}