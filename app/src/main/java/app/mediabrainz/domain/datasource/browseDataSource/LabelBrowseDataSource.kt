package app.mediabrainz.domain.datasource.browseDataSource

import androidx.paging.PageKeyedDataSource
import app.mediabrainz.api.ApiRequestProvider
import app.mediabrainz.api.browserequest.LabelBrowseEntityType
import app.mediabrainz.api.response.LabelBrowseResponse
import app.mediabrainz.api.response.LabelResponse
import app.mediabrainz.domain.datasource.core.BaseDataSource
import app.mediabrainz.domain.datasource.core.DataSourceFactory
import app.mediabrainz.domain.mapper.LabelMapper
import app.mediabrainz.domain.model.Label


class LabelBrowseDataSource(val entityType: LabelBrowseEntityType, val mbid: String) :
    BaseDataSource<LabelResponse, Label, LabelBrowseResponse>() {

    override fun request(loadSize: Int, offset: Int) =
        ApiRequestProvider.createLabelBrowseRequest(entityType, mbid)
            .browse(loadSize, offset)

    override fun map() = LabelMapper()::mapTo

    class Factory(val entityType: LabelBrowseEntityType, val mbid: String) :
        DataSourceFactory<Label>() {

        override fun create(): PageKeyedDataSource<Int, Label> {
            val dataSource = LabelBrowseDataSource(entityType, mbid)
            setDataSource(dataSource)
            return dataSource
        }
    }
}