package app.mediabrainz.domain.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import app.mediabrainz.api.ApiRequestProvider
import app.mediabrainz.api.response.UrlResponse
import app.mediabrainz.api.response.UrlSearchResponse
import app.mediabrainz.domain.mapper.UrlMapper
import app.mediabrainz.domain.model.Url
import app.mediabrainz.domain.parenthesesString


class UrlSearchDataSource(val query: String) :
    BaseSearchDataSource<UrlResponse, Url, UrlSearchResponse>() {

    override fun request(loadSize: Int, offset: Int) = ApiRequestProvider.createUrlSearchRequest()
        .search(parenthesesString(query), loadSize, offset)

    override fun map() = UrlMapper()::mapTo

    class Factory(val query: String) : DataSource.Factory<Int, Url>() {
        val dataSourceLiveData = MutableLiveData<DataSourceInterface>()

        override fun create(): PageKeyedDataSource<Int, Url> {
            val dataSource = UrlSearchDataSource(query)
            dataSourceLiveData.postValue(dataSource)
            return dataSource
        }
    }

}