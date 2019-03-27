package app.mediabrainz.domain.datasource.searchDataSource

import androidx.paging.PageKeyedDataSource
import app.mediabrainz.api.ApiRequestProvider
import app.mediabrainz.api.response.AnnotationResponse
import app.mediabrainz.api.response.AnnotationSearchResponse
import app.mediabrainz.domain.datasource.core.BaseDataSource
import app.mediabrainz.domain.datasource.core.DataSourceFactory
import app.mediabrainz.domain.mapper.AnnotationMapper
import app.mediabrainz.domain.model.Annotation
import app.mediabrainz.domain.parenthesesString


class AnnotationSearchDataSource(val query: String) :
    BaseDataSource<AnnotationResponse, Annotation, AnnotationSearchResponse>() {

    override fun request(loadSize: Int, offset: Int) = ApiRequestProvider.createAnnotationSearchRequest()
        .search(parenthesesString(query), loadSize, offset)

    override fun map() = AnnotationMapper()::mapTo

    class Factory(val query: String) : DataSourceFactory<Annotation>() {
        override fun create(): PageKeyedDataSource<Int, Annotation> {
            val dataSource = AnnotationSearchDataSource(query)
            setDataSource(dataSource)
            return dataSource
        }
    }

}