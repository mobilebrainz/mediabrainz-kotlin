package app.mediabrainz.api.coverartrequest

import app.mediabrainz.api.response.ReleaseCoverArtResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response


interface CoverArtRequestInterface {

    fun getReleaseCoverArt(): Deferred<Response<ReleaseCoverArtResponse>>

    fun getReleaseGroupCoverArt(): Deferred<Response<ReleaseCoverArtResponse>>

}