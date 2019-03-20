package app.mediabrainz.domain.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import app.mediabrainz.api.ApiRequestProvider
import app.mediabrainz.api.response.AnnotationResponse
import app.mediabrainz.api.response.AnnotationSearchResponse
import app.mediabrainz.domain.mapper.AnnotationMapper
import app.mediabrainz.domain.model.Annotation
import app.mediabrainz.domain.parenthesesString


class AnnotationSearchDataSource(val query: String) :
    BaseSearchDataSource<AnnotationResponse, Annotation, AnnotationSearchResponse>() {

    override fun request(loadSize: Int, offset: Int) = ApiRequestProvider.createAnnotationSearchRequest()
        .search(parenthesesString(query), loadSize, offset)

    override fun map() = AnnotationMapper()::mapTo

    class Factory(val query: String) : DataSource.Factory<Int, Annotation>() {
        val dataSourceLiveData = MutableLiveData<DataSourceInterface>()

        override fun create(): PageKeyedDataSource<Int, Annotation> {
            val dataSource = AnnotationSearchDataSource(query)
            dataSourceLiveData.postValue(dataSource)
            return dataSource
        }
    }

}