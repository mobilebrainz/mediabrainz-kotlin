package app.mediabrainz.domain.datasource.browseDataSource

import androidx.paging.PageKeyedDataSource
import app.mediabrainz.api.ApiRequestProvider
import app.mediabrainz.api.browserequest.ReleaseGroupBrowseEntityType
import app.mediabrainz.api.browserequest.ReleaseGroupBrowseIncType.*
import app.mediabrainz.api.response.ReleaseGroupBrowseResponse
import app.mediabrainz.api.response.ReleaseGroupResponse
import app.mediabrainz.domain.OAuthManager
import app.mediabrainz.domain.datasource.core.BaseDataSource
import app.mediabrainz.domain.datasource.core.DataSourceFactory
import app.mediabrainz.domain.mapper.ReleaseGroupMapper
import app.mediabrainz.domain.model.ReleaseGroup


class ReleaseGroupBrowseDataSource(
    val entityType: ReleaseGroupBrowseEntityType,
    val mbid: String,
    val authorized: Boolean
) :
    BaseDataSource<ReleaseGroupResponse, ReleaseGroup, ReleaseGroupBrowseResponse>() {

    override fun request(loadSize: Int, offset: Int) =
        if (authorized)
            ApiRequestProvider.createReleaseGroupBrowseRequest(entityType, mbid)
                .addIncs(USER_TAGS, USER_GENRES, USER_RATINGS)
                .addAccessToken(OAuthManager.accessToken?.token ?: "")
                .browse(loadSize, offset)
        else
            ApiRequestProvider.createReleaseGroupBrowseRequest(entityType, mbid)
                .browse(loadSize, offset)

    override fun map() = ReleaseGroupMapper()::mapTo

    override fun isAuthorized() = authorized

    class Factory(val entityType: ReleaseGroupBrowseEntityType, val mbid: String, val authorized: Boolean = false) :
        DataSourceFactory<ReleaseGroup>() {

        override fun create(): PageKeyedDataSource<Int, ReleaseGroup> {
            val dataSource = ReleaseGroupBrowseDataSource(entityType, mbid, authorized)
            setDataSource(dataSource)
            return dataSource
        }
    }
}