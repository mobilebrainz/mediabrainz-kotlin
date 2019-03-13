package app.mediabrainz.domain.repository

import app.mediabrainz.api.response.ArtistSearchResponse
import app.mediabrainz.api.ApiServiceProvider
import app.mediabrainz.domain.mapper.ArtistMapper
import app.mediabrainz.domain.model.Artist


class ArtistSearchRepository : BaseApiRepository<ArtistSearchResponse, List<Artist>>() {

    fun search(artist: String, limit: Int, offset: Int) {
        mutableLiveData.value = Resource.loading(null)
        recursiveSearch(artist, limit, offset)
    }

    private fun recursiveSearch(artist: String, limit: Int, offset: Int) {
        val request = ApiServiceProvider.createArtistSearchService().search(artist, limit, offset)
        call(request,
            { recursiveSearch(artist, limit, offset) },
            { ArtistMapper().mapToList(artists) })
    }

}