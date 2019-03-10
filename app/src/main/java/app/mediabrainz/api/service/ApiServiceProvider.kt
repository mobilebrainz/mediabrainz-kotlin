package app.mediabrainz.api.service


object ApiServiceProvider {

    private val artistSearchService: ArtistSearchService by lazy {
        return@lazy ArtistSearchService()
    }

    fun createArtistSearchService(): ArtistSearchService {
        artistSearchService.clear()
        return artistSearchService
    }

}