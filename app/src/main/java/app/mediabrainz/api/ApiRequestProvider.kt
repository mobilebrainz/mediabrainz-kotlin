package app.mediabrainz.api

import app.mediabrainz.api.searchrequest.AnnotationSearchRequest
import app.mediabrainz.api.searchrequest.ArtistSearchRequest


object ApiRequestProvider {

    fun createAnnotationSearchRequest() = AnnotationSearchRequest()
    fun createArtistSearchRequest() = ArtistSearchRequest()

}