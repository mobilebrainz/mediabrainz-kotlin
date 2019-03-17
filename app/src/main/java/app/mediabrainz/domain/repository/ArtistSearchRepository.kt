package app.mediabrainz.domain.repository

import app.mediabrainz.api.ApiRequestProvider
import app.mediabrainz.api.response.ArtistSearchResponse
import app.mediabrainz.domain.mapper.ArtistMapper
import app.mediabrainz.domain.model.Artist
import app.mediabrainz.domain.parenthesesString


class ArtistSearchRepository : BaseApiRepository<ArtistSearchResponse, List<Artist>>() {

    fun search(artist: String, limit: Int, offset: Int) {
        mutableLiveData.value = Resource.loading()
        recursiveSearch(artist, limit, offset)
    }

    private fun recursiveSearch(artist: String, limit: Int, offset: Int) {
        val request = ApiRequestProvider.createArtistSearchRequest()
            .search(parenthesesString(artist), limit, offset)
        call(request,
            { recursiveSearch(artist, limit, offset) },
            { ArtistMapper().mapToList(getItems()) })
    }

}