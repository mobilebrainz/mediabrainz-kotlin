package app.mediabrainz.domain.datasource.browseDataSource

import androidx.paging.PageKeyedDataSource
import app.mediabrainz.api.ApiRequestProvider
import app.mediabrainz.api.browserequest.PlaceBrowseEntityType
import app.mediabrainz.api.response.PlaceBrowseResponse
import app.mediabrainz.api.response.PlaceResponse
import app.mediabrainz.domain.datasource.core.BaseDataSource
import app.mediabrainz.domain.datasource.core.DataSourceFactory
import app.mediabrainz.domain.mapper.PlaceMapper
import app.mediabrainz.domain.model.Place


class PlaceBrowseDataSource(val entityType: PlaceBrowseEntityType, val mbid: String) :
    BaseDataSource<PlaceResponse, Place, PlaceBrowseResponse>() {

    override fun request(loadSize: Int, offset: Int) =
        ApiRequestProvider.createPlaceBrowseRequest(entityType, mbid)
            .browse(loadSize, offset)

    override fun map() = PlaceMapper()::mapTo

    class Factory(val entityType: PlaceBrowseEntityType, val mbid: String) :
        DataSourceFactory<Place>() {

        override fun create(): PageKeyedDataSource<Int, Place> {
            val dataSource = PlaceBrowseDataSource(entityType, mbid)
            setDataSource(dataSource)
            return dataSource
        }
    }
}