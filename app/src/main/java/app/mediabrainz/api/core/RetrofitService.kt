package app.mediabrainz.api.core

import app.mediabrainz.api.core.Config.ARTIST_QUERY
import app.mediabrainz.api.response.ArtistBrowseResponse
import app.mediabrainz.api.response.ArtistResponse
import app.mediabrainz.api.response.ArtistSearchResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap


interface RetrofitService {

    @GET(ARTIST_QUERY)
    fun searchArtist(@QueryMap params: Map<String, String>): Deferred<Response<ArtistSearchResponse>>

    @GET(ARTIST_QUERY)
    fun browseArtist(@QueryMap params: Map<String, String>): Deferred<Response<ArtistBrowseResponse>>

    @GET(ARTIST_QUERY + "/{mbid}")
    fun lookupArtist(
        @Path("mbid") mbid: String,
        @QueryMap params: Map<String, String>
    ): Deferred<Response<ArtistResponse>>

}