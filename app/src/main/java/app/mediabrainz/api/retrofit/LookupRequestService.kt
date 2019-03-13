package app.mediabrainz.api.retrofit

import app.mediabrainz.api.core.Config.ARTIST_QUERY
import app.mediabrainz.api.response.ArtistResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap


interface LookupRequestService {

    @GET(ARTIST_QUERY + "/{mbid}")
    fun lookupArtist(
        @Path("mbid") mbid: String,
        @QueryMap params: Map<String, String>
    ): Deferred<Response<ArtistResponse>>

}