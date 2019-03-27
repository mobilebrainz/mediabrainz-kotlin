package app.mediabrainz.domain.datasource.searchDataSource

import androidx.paging.PageKeyedDataSource
import app.mediabrainz.api.ApiRequestProvider
import app.mediabrainz.api.response.UrlResponse
import app.mediabrainz.api.response.UrlSearchResponse
import app.mediabrainz.domain.datasource.core.BaseDataSource
import app.mediabrainz.domain.datasource.core.DataSourceFactory
import app.mediabrainz.domain.mapper.UrlMapper
import app.mediabrainz.domain.model.Url
import app.mediabrainz.domain.parenthesesString


class UrlSearchDataSource(val query: String) :
    BaseDataSource<UrlResponse, Url, UrlSearchResponse>() {

    override fun request(loadSize: Int, offset: Int) = ApiRequestProvider.createUrlSearchRequest()
        .search(parenthesesString(query), loadSize, offset)

    override fun map() = UrlMapper()::mapTo

    class Factory(val query: String) : DataSourceFactory<Url>() {
        override fun create(): PageKeyedDataSource<Int, Url> {
            val dataSource = UrlSearchDataSource(query)
            setDataSource(dataSource)
            return dataSource
        }
    }

}