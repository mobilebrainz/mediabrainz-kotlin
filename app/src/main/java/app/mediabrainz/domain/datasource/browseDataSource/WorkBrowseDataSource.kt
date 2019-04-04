package app.mediabrainz.domain.datasource.browseDataSource

import androidx.paging.PageKeyedDataSource
import app.mediabrainz.api.ApiRequestProvider
import app.mediabrainz.api.browserequest.WorkBrowseEntityType
import app.mediabrainz.api.browserequest.WorkBrowseIncType.USER_RATINGS
import app.mediabrainz.api.browserequest.WorkBrowseIncType.USER_TAGS
import app.mediabrainz.api.response.WorkBrowseResponse
import app.mediabrainz.api.response.WorkResponse
import app.mediabrainz.domain.OAuthManager
import app.mediabrainz.domain.datasource.core.BaseDataSource
import app.mediabrainz.domain.datasource.core.DataSourceFactory
import app.mediabrainz.domain.mapper.WorkMapper
import app.mediabrainz.domain.model.Work


class WorkBrowseDataSource(val entityType: WorkBrowseEntityType, val mbid: String, val authorized: Boolean) :
    BaseDataSource<WorkResponse, Work, WorkBrowseResponse>() {

    override fun request(loadSize: Int, offset: Int) =
        if (authorized)
            ApiRequestProvider.createWorkBrowseRequest(entityType, mbid)
                .addIncs(USER_TAGS, USER_RATINGS)
                .addAccessToken(OAuthManager.accessToken?.token ?: "")
                .browse(loadSize, offset)
        else
            ApiRequestProvider.createWorkBrowseRequest(entityType, mbid)
                .browse(loadSize, offset)

    override fun map() = WorkMapper()::mapTo

    override fun isAuthorized() = authorized

    class Factory(val entityType: WorkBrowseEntityType, val mbid: String, val authorized: Boolean = false) :
        DataSourceFactory<Work>() {

        override fun create(): PageKeyedDataSource<Int, Work> {
            val dataSource = WorkBrowseDataSource(entityType, mbid, authorized)
            setDataSource(dataSource)
            return dataSource
        }
    }
}