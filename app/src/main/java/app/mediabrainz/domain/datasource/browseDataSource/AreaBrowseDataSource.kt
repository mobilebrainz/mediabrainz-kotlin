package app.mediabrainz.domain.datasource.browseDataSource

import androidx.paging.PageKeyedDataSource
import app.mediabrainz.api.ApiRequestProvider
import app.mediabrainz.api.browserequest.AreaBrowseEntityType
import app.mediabrainz.api.browserequest.AreaBrowseIncType
import app.mediabrainz.api.response.AreaBrowseResponse
import app.mediabrainz.api.response.AreaResponse
import app.mediabrainz.domain.OAuthManager
import app.mediabrainz.domain.datasource.core.BaseDataSource
import app.mediabrainz.domain.datasource.core.DataSourceFactory
import app.mediabrainz.domain.mapper.AreaMapper
import app.mediabrainz.domain.model.Area


class AreaBrowseDataSource(val entityType: AreaBrowseEntityType, val mbid: String, val authorized: Boolean) :
    BaseDataSource<AreaResponse, Area, AreaBrowseResponse>() {

    override fun request(loadSize: Int, offset: Int) =
        if (authorized)
            ApiRequestProvider.createAreaBrowseRequest(entityType, mbid)
                .addIncs(AreaBrowseIncType.USER_TAGS)
                .addAccessToken(OAuthManager.accessToken?.token ?: "")
                .browse(loadSize, offset)
        else
            ApiRequestProvider.createAreaBrowseRequest(entityType, mbid)
                .browse(loadSize, offset)

    override fun map() = AreaMapper()::mapTo

    override fun isAuthorized() = authorized

    class Factory(val entityType: AreaBrowseEntityType, val mbid: String, val authorized: Boolean = false) :
        DataSourceFactory<Area>() {

        override fun create(): PageKeyedDataSource<Int, Area> {
            val dataSource = AreaBrowseDataSource(entityType, mbid, authorized)
            setDataSource(dataSource)
            return dataSource
        }
    }
}