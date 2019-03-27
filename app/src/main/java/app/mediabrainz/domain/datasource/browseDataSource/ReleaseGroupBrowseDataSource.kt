package app.mediabrainz.domain.datasource.browseDataSource

import androidx.paging.PageKeyedDataSource
import app.mediabrainz.api.ApiRequestProvider
import app.mediabrainz.api.browserequest.ReleaseGroupBrowseEntityType
import app.mediabrainz.api.response.ReleaseGroupBrowseResponse
import app.mediabrainz.api.response.ReleaseGroupResponse
import app.mediabrainz.domain.datasource.core.BaseDataSource
import app.mediabrainz.domain.datasource.core.DataSourceFactory
import app.mediabrainz.domain.mapper.ReleaseGroupMapper
import app.mediabrainz.domain.model.ReleaseGroup


class ReleaseGroupBrowseDataSource(val entityType: ReleaseGroupBrowseEntityType, val mbid: String) :
    BaseDataSource<ReleaseGroupResponse, ReleaseGroup, ReleaseGroupBrowseResponse>() {

    override fun request(loadSize: Int, offset: Int) =
        ApiRequestProvider.createReleaseGroupBrowseRequest(entityType, mbid)
            .browse(loadSize, offset)

    override fun map() = ReleaseGroupMapper()::mapTo

    class Factory(val entityType: ReleaseGroupBrowseEntityType, val mbid: String) :
        DataSourceFactory<ReleaseGroup>() {

        override fun create(): PageKeyedDataSource<Int, ReleaseGroup> {
            val dataSource = ReleaseGroupBrowseDataSource(entityType, mbid)
            setDataSource(dataSource)
            return dataSource
        }
    }
}