package app.mediabrainz.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.mediabrainz.api.response.ArtistSearchResponse
import app.mediabrainz.domain.repository.ArtistSearchRepository
import app.mediabrainz.domain.repository.ArtistSearchRepository2
import app.mediabrainz.domain.repository.Resource


class SearchViewModel2 : ViewModel() {

    lateinit var artistsResource: MutableLiveData<Resource<ArtistSearchResponse?>>
    var query: String = ""
    var limit: Int = 0
    var offset: Int = 0

    fun fetchArtists(artist: String, limit: Int, offset: Int) {
        if (artistsResource.value == null || query != artist || this.offset != offset) {
            query = artist
            this.limit = limit
            this.offset = offset
            loadArtists()
        }
    }

    // retry when error
    fun loadArtists() {
        if (query != "" && limit > 0) {
            artistsResource = ArtistSearchRepository2().search(query, limit, offset)
        }
    }

}