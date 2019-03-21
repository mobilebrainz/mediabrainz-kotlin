package app.mediabrainz.domain.datasource

import androidx.paging.PageKeyedDataSource
import app.mediabrainz.api.ApiRequestProvider
import app.mediabrainz.api.response.PlaceResponse
import app.mediabrainz.api.response.PlaceSearchResponse
import app.mediabrainz.domain.datasource.core.DataSourceFactory
import app.mediabrainz.domain.mapper.PlaceMapper
import app.mediabrainz.domain.model.Place
import app.mediabrainz.domain.parenthesesString


class PlaceSearchDataSource(val query: String) :
    BaseSearchDataSource<PlaceResponse, Place, PlaceSearchResponse>() {

    override fun request(loadSize: Int, offset: Int) = ApiRequestProvider.createPlaceSearchRequest()
        .search(parenthesesString(query), loadSize, offset)

    override fun map() = PlaceMapper()::mapTo

    class Factory(val query: String) : DataSourceFactory<Place>() {
        override fun create(): PageKeyedDataSource<Int, Place> {
            val dataSource = PlaceSearchDataSource(query)
            setDataSource(dataSource)
            return dataSource
        }
    }

}