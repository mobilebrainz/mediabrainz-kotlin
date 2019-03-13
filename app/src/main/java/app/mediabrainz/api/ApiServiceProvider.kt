package app.mediabrainz.api

import app.mediabrainz.api.searchservice.AnnotationSearchService
import app.mediabrainz.api.searchservice.ArtistSearchService


object ApiServiceProvider {

    fun createAnnotationSearchService() = AnnotationSearchService()
    fun createArtistSearchService() = ArtistSearchService()

}