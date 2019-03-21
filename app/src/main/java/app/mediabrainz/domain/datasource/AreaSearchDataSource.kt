package app.mediabrainz.domain.datasource

import androidx.paging.PageKeyedDataSource
import app.mediabrainz.api.ApiRequestProvider
import app.mediabrainz.api.response.AreaResponse
import app.mediabrainz.api.response.AreaSearchResponse
import app.mediabrainz.domain.datasource.core.DataSourceFactory
import app.mediabrainz.domain.mapper.AreaMapper
import app.mediabrainz.domain.model.Area
import app.mediabrainz.domain.parenthesesString


class AreaSearchDataSource(val query: String) :
    BaseSearchDataSource<AreaResponse, Area, AreaSearchResponse>() {

    override fun request(loadSize: Int, offset: Int) = ApiRequestProvider.createAreaSearchRequest()
        .search(parenthesesString(query), loadSize, offset)

    override fun map() = AreaMapper()::mapTo

    class Factory(val query: String) : DataSourceFactory<Area>() {
        override fun create(): PageKeyedDataSource<Int, Area> {
            val dataSource = AreaSearchDataSource(query)
            setDataSource(dataSource)
            return dataSource
        }
    }

}