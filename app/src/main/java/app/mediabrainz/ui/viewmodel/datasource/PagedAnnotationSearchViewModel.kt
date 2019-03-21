package app.mediabrainz.ui.viewmodel.datasource

import app.mediabrainz.domain.datasource.AnnotationSearchDataSource
import app.mediabrainz.domain.model.Annotation


class PagedAnnotationSearchViewModel : BaseDataSourceViewModel<Annotation>() {

    private val initialLoadSize = 25
    private val loadSize = 25

    fun search(query: String) {
        val factory = AnnotationSearchDataSource.Factory(query)
        val config = buildPagedListConfig(loadSize, initialLoadSize)
        initPagedItems(config, factory)
    }

}