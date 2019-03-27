package app.mediabrainz.domain.datasource.browseDataSource

import androidx.paging.PageKeyedDataSource
import app.mediabrainz.api.ApiRequestProvider
import app.mediabrainz.api.browserequest.CollectionBrowseEntityType
import app.mediabrainz.api.response.CollectionBrowseResponse
import app.mediabrainz.api.response.CollectionResponse
import app.mediabrainz.domain.datasource.core.BaseDataSource
import app.mediabrainz.domain.datasource.core.DataSourceFactory
import app.mediabrainz.domain.mapper.CollectionMapper
import app.mediabrainz.domain.model.Collection


class CollectionBrowseDataSource(val entityType: CollectionBrowseEntityType, val mbid: String) :
    BaseDataSource<CollectionResponse, Collection, CollectionBrowseResponse>() {

    override fun request(loadSize: Int, offset: Int) =
        ApiRequestProvider.createCollectionBrowseRequest(entityType, mbid)
            .browse(loadSize, offset)

    override fun map() = CollectionMapper()::mapTo

    class Factory(val entityType: CollectionBrowseEntityType, val mbid: String) :
        DataSourceFactory<Collection>() {

        override fun create(): PageKeyedDataSource<Int, Collection> {
            val dataSource = CollectionBrowseDataSource(entityType, mbid)
            setDataSource(dataSource)
            return dataSource
        }
    }
}