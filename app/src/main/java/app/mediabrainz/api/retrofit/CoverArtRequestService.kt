package app.mediabrainz.api.retrofit

import app.mediabrainz.api.response.ReleaseCoverArtResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface CoverArtRequestService {

    companion object {
        const val COVERART_WEB_SERVICE = "http://coverartarchive.org"
        const val RELEASE_PATH = "release"
        const val RELEASE_GROUP_PATH = "release-group"
    }

    @GET("$COVERART_WEB_SERVICE/{path}/{mbid}")
    fun getCoverArts(
        @Path("path") path: String,
        @Path("mbid") mbid: String
    ): Deferred<Response<ReleaseCoverArtResponse>>
}