package app.mediabrainz.api

import app.mediabrainz.api.searchrequest.AnnotationSearchRequest
import app.mediabrainz.api.searchrequest.ArtistSearchRequest
import app.mediabrainz.api.searchrequest.ReleaseGroupSearchRequest


object ApiRequestProvider {

    fun createAnnotationSearchRequest() = AnnotationSearchRequest()
    fun createArtistSearchRequest() = ArtistSearchRequest()
    fun createReleaseGroupSearchRequest() = ReleaseGroupSearchRequest()

}