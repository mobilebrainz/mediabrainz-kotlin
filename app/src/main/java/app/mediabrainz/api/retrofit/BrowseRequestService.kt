package app.mediabrainz.api.retrofit

import app.mediabrainz.api.core.Config.ARTIST_QUERY
import app.mediabrainz.api.response.ArtistBrowseResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap


interface BrowseRequestService {

    @GET(ARTIST_QUERY)
    fun browseArtist(@QueryMap params: Map<String, String>): Deferred<Response<ArtistBrowseResponse>>

}