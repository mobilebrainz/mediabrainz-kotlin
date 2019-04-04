package app.mediabrainz.domain.datasource.browseDataSource

import androidx.paging.PageKeyedDataSource
import app.mediabrainz.api.ApiRequestProvider
import app.mediabrainz.api.browserequest.PlaceBrowseEntityType
import app.mediabrainz.api.browserequest.PlaceBrowseIncType
import app.mediabrainz.api.response.PlaceBrowseResponse
import app.mediabrainz.api.response.PlaceResponse
import app.mediabrainz.domain.OAuthManager
import app.mediabrainz.domain.datasource.core.BaseDataSource
import app.mediabrainz.domain.datasource.core.DataSourceFactory
import app.mediabrainz.domain.mapper.PlaceMapper
import app.mediabrainz.domain.model.Place


class PlaceBrowseDataSource(val entityType: PlaceBrowseEntityType, val mbid: String, val authorized: Boolean) :
    BaseDataSource<PlaceResponse, Place, PlaceBrowseResponse>() {

    override fun request(loadSize: Int, offset: Int) =
        if (authorized)
            ApiRequestProvider.createPlaceBrowseRequest(entityType, mbid)
                .addIncs(PlaceBrowseIncType.USER_TAGS)
                .addAccessToken(OAuthManager.accessToken?.token ?: "")
                .browse(loadSize, offset)
        else
            ApiRequestProvider.createPlaceBrowseRequest(entityType, mbid)
                .browse(loadSize, offset)

    override fun map() = PlaceMapper()::mapTo

    override fun isAuthorized() = authorized

    class Factory(val entityType: PlaceBrowseEntityType, val mbid: String, val authorized: Boolean = false) :
        DataSourceFactory<Place>() {

        override fun create(): PageKeyedDataSource<Int, Place> {
            val dataSource = PlaceBrowseDataSource(entityType, mbid, authorized)
            setDataSource(dataSource)
            return dataSource
        }
    }
}