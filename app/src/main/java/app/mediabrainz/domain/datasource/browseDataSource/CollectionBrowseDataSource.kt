package app.mediabrainz.domain.datasource.browseDataSource

import androidx.paging.PageKeyedDataSource
import app.mediabrainz.api.ApiRequestProvider
import app.mediabrainz.api.browserequest.CollectionBrowseEntityType
import app.mediabrainz.api.browserequest.CollectionBrowseIncType
import app.mediabrainz.api.response.CollectionBrowseResponse
import app.mediabrainz.api.response.CollectionResponse
import app.mediabrainz.domain.OAuthManager
import app.mediabrainz.domain.datasource.core.BaseDataSource
import app.mediabrainz.domain.datasource.core.DataSourceFactory
import app.mediabrainz.domain.mapper.CollectionMapper
import app.mediabrainz.domain.model.Collection


class CollectionBrowseDataSource(
    val entityType: CollectionBrowseEntityType,
    val mbid: String,
    val authorized: Boolean
) :
    BaseDataSource<CollectionResponse, Collection, CollectionBrowseResponse>() {

    override fun request(loadSize: Int, offset: Int) =
        if (authorized)
            ApiRequestProvider.createCollectionBrowseRequest(entityType, mbid)
                .addIncs(CollectionBrowseIncType.USER_COLLECTIONS)
                .addAccessToken(OAuthManager.accessToken?.token ?: "")
                .browse(loadSize, offset)
        else
            ApiRequestProvider.createCollectionBrowseRequest(entityType, mbid)
                .browse(loadSize, offset)

    override fun map() = CollectionMapper()::mapTo

    override fun isAuthorized() = authorized

    class Factory(val entityType: CollectionBrowseEntityType, val mbid: String, val authorized: Boolean = false) :
        DataSourceFactory<Collection>() {

        override fun create(): PageKeyedDataSource<Int, Collection> {
            val dataSource = CollectionBrowseDataSource(entityType, mbid, authorized)
            setDataSource(dataSource)
            return dataSource
        }
    }
}