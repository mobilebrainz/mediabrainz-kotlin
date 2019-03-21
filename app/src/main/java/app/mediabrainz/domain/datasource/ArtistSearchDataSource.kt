package app.mediabrainz.domain.datasource

import androidx.paging.PageKeyedDataSource
import app.mediabrainz.api.ApiRequestProvider
import app.mediabrainz.api.response.ArtistResponse
import app.mediabrainz.api.response.ArtistSearchResponse
import app.mediabrainz.domain.datasource.core.DataSourceFactory
import app.mediabrainz.domain.mapper.ArtistMapper
import app.mediabrainz.domain.model.Artist
import app.mediabrainz.domain.parenthesesString


class ArtistSearchDataSource(val query: String) : BaseSearchDataSource<ArtistResponse, Artist, ArtistSearchResponse>() {

    override fun request(loadSize: Int, offset: Int) = ApiRequestProvider.createArtistSearchRequest()
        .search(parenthesesString(query), loadSize, offset)

    override fun map() = ArtistMapper()::mapTo

    class Factory(val query: String) : DataSourceFactory<Artist>() {
        override fun create(): PageKeyedDataSource<Int, Artist> {
            val dataSource = ArtistSearchDataSource(query)
            setDataSource(dataSource)
            return dataSource
        }
    }
}