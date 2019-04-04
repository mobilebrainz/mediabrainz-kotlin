package app.mediabrainz.domain.datasource.browseDataSource

import androidx.paging.PageKeyedDataSource
import app.mediabrainz.api.ApiRequestProvider
import app.mediabrainz.api.browserequest.ArtistBrowseEntityType
import app.mediabrainz.api.browserequest.ArtistBrowseIncType.*
import app.mediabrainz.api.response.ArtistBrowseResponse
import app.mediabrainz.api.response.ArtistResponse
import app.mediabrainz.domain.OAuthManager
import app.mediabrainz.domain.datasource.core.BaseDataSource
import app.mediabrainz.domain.datasource.core.DataSourceFactory
import app.mediabrainz.domain.mapper.ArtistMapper
import app.mediabrainz.domain.model.Artist


class ArtistBrowseDataSource(val entityType: ArtistBrowseEntityType, val mbid: String, val authorized: Boolean) :
    BaseDataSource<ArtistResponse, Artist, ArtistBrowseResponse>() {

    override fun request(loadSize: Int, offset: Int) =
        if (authorized)
            ApiRequestProvider.createArtistBrowseRequest(entityType, mbid)
                .addIncs(USER_TAGS, USER_GENRES, USER_RATINGS)
                .addAccessToken(OAuthManager.accessToken?.token ?: "")
                .browse(loadSize, offset)
        else
            ApiRequestProvider.createArtistBrowseRequest(entityType, mbid)
                .browse(loadSize, offset)

    override fun map() = ArtistMapper()::mapTo

    override fun isAuthorized() = authorized

    class Factory(val entityType: ArtistBrowseEntityType, val mbid: String, val authorized: Boolean = false) :
        DataSourceFactory<Artist>() {

        override fun create(): PageKeyedDataSource<Int, Artist> {
            val dataSource = ArtistBrowseDataSource(entityType, mbid, authorized)
            setDataSource(dataSource)
            return dataSource
        }
    }
}