package app.mediabrainz.domain.datasource.browseDataSource

import androidx.paging.PageKeyedDataSource
import app.mediabrainz.api.ApiRequestProvider
import app.mediabrainz.api.browserequest.ArtistBrowseEntityType
import app.mediabrainz.api.response.ArtistBrowseResponse
import app.mediabrainz.api.response.ArtistResponse
import app.mediabrainz.domain.datasource.core.BaseDataSource
import app.mediabrainz.domain.datasource.core.DataSourceFactory
import app.mediabrainz.domain.mapper.ArtistMapper
import app.mediabrainz.domain.model.Artist


class ArtistBrowseDataSource(val entityType: ArtistBrowseEntityType, val mbid: String) :
    BaseDataSource<ArtistResponse, Artist, ArtistBrowseResponse>() {

    override fun request(loadSize: Int, offset: Int) =
        ApiRequestProvider.createArtistBrowseRequest(entityType, mbid)
            .browse(loadSize, offset)

    override fun map() = ArtistMapper()::mapTo

    class Factory(val entityType: ArtistBrowseEntityType, val mbid: String) :
        DataSourceFactory<Artist>() {

        override fun create(): PageKeyedDataSource<Int, Artist> {
            val dataSource = ArtistBrowseDataSource(entityType, mbid)
            setDataSource(dataSource)
            return dataSource
        }
    }
}