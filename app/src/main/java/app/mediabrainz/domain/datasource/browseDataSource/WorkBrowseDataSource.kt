package app.mediabrainz.domain.datasource.browseDataSource

import androidx.paging.PageKeyedDataSource
import app.mediabrainz.api.ApiRequestProvider
import app.mediabrainz.api.browserequest.WorkBrowseEntityType
import app.mediabrainz.api.response.WorkBrowseResponse
import app.mediabrainz.api.response.WorkResponse
import app.mediabrainz.domain.datasource.core.BaseDataSource
import app.mediabrainz.domain.datasource.core.DataSourceFactory
import app.mediabrainz.domain.mapper.WorkMapper
import app.mediabrainz.domain.model.Work


class WorkBrowseDataSource(val entityType: WorkBrowseEntityType, val mbid: String) :
    BaseDataSource<WorkResponse, Work, WorkBrowseResponse>() {

    override fun request(loadSize: Int, offset: Int) =
        ApiRequestProvider.createWorkBrowseRequest(entityType, mbid)
            .browse(loadSize, offset)

    override fun map() = WorkMapper()::mapTo

    class Factory(val entityType: WorkBrowseEntityType, val mbid: String) :
        DataSourceFactory<Work>() {

        override fun create(): PageKeyedDataSource<Int, Work> {
            val dataSource = WorkBrowseDataSource(entityType, mbid)
            setDataSource(dataSource)
            return dataSource
        }
    }
}