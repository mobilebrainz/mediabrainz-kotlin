package app.mediabrainz.domain.datasource.browseDataSource

import androidx.paging.PageKeyedDataSource
import app.mediabrainz.api.ApiRequestProvider
import app.mediabrainz.api.browserequest.AreaBrowseEntityType
import app.mediabrainz.api.response.AreaBrowseResponse
import app.mediabrainz.api.response.AreaResponse
import app.mediabrainz.domain.datasource.core.BaseDataSource
import app.mediabrainz.domain.datasource.core.DataSourceFactory
import app.mediabrainz.domain.mapper.AreaMapper
import app.mediabrainz.domain.model.Area


class AreaBrowseDataSource(val entityType: AreaBrowseEntityType, val mbid: String) :
    BaseDataSource<AreaResponse, Area, AreaBrowseResponse>() {

    override fun request(loadSize: Int, offset: Int) =
        ApiRequestProvider.createAreaBrowseRequest(entityType, mbid)
            .browse(loadSize, offset)

    override fun map() = AreaMapper()::mapTo

    class Factory(val entityType: AreaBrowseEntityType, val mbid: String) :
        DataSourceFactory<Area>() {

        override fun create(): PageKeyedDataSource<Int, Area> {
            val dataSource = AreaBrowseDataSource(entityType, mbid)
            setDataSource(dataSource)
            return dataSource
        }
    }
}