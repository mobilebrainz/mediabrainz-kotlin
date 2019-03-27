package app.mediabrainz.domain.datasource.browseDataSource

import androidx.paging.PageKeyedDataSource
import app.mediabrainz.api.ApiRequestProvider
import app.mediabrainz.api.browserequest.RecordingBrowseEntityType
import app.mediabrainz.api.response.RecordingBrowseResponse
import app.mediabrainz.api.response.RecordingResponse
import app.mediabrainz.domain.datasource.core.BaseDataSource
import app.mediabrainz.domain.datasource.core.DataSourceFactory
import app.mediabrainz.domain.mapper.RecordingMapper
import app.mediabrainz.domain.model.Recording


class RecordingBrowseDataSource(val entityType: RecordingBrowseEntityType, val mbid: String) :
    BaseDataSource<RecordingResponse, Recording, RecordingBrowseResponse>() {

    override fun request(loadSize: Int, offset: Int) =
        ApiRequestProvider.createRecordingBrowseRequest(entityType, mbid)
            .browse(loadSize, offset)

    override fun map() = RecordingMapper()::mapTo

    class Factory(val entityType: RecordingBrowseEntityType, val mbid: String) :
        DataSourceFactory<Recording>() {

        override fun create(): PageKeyedDataSource<Int, Recording> {
            val dataSource = RecordingBrowseDataSource(entityType, mbid)
            setDataSource(dataSource)
            return dataSource
        }
    }
}