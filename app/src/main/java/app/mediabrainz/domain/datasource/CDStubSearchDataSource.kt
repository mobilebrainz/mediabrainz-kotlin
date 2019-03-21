package app.mediabrainz.domain.datasource

import androidx.paging.PageKeyedDataSource
import app.mediabrainz.api.ApiRequestProvider
import app.mediabrainz.api.response.CDStubResponse
import app.mediabrainz.api.response.CDStubSearchResponse
import app.mediabrainz.domain.datasource.core.DataSourceFactory
import app.mediabrainz.domain.mapper.CDStubMapper
import app.mediabrainz.domain.model.CDStub
import app.mediabrainz.domain.parenthesesString


class CDStubSearchDataSource(val query: String) :
    BaseSearchDataSource<CDStubResponse, CDStub, CDStubSearchResponse>() {

    override fun request(loadSize: Int, offset: Int) = ApiRequestProvider.createCDStubSearchRequest()
        .search(parenthesesString(query), loadSize, offset)

    override fun map() = CDStubMapper()::mapTo

    class Factory(val query: String) : DataSourceFactory<CDStub>() {
        override fun create(): PageKeyedDataSource<Int, CDStub> {
            val dataSource = CDStubSearchDataSource(query)
            setDataSource(dataSource)
            return dataSource
        }
    }

}