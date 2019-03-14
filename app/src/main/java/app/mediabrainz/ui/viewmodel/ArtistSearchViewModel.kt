package app.mediabrainz.ui.viewmodel

import androidx.lifecycle.ViewModel
import app.mediabrainz.domain.repository.ArtistSearchRepository


class ArtistSearchViewModel : ViewModel() {

    private val artistSearchRepository = ArtistSearchRepository()
    val artistsResource = artistSearchRepository.mutableLiveData

    private var query: String = ""
    private var limit: Int = 0
    private var offset: Int = 0

    fun searchArtists(artist: String, limit: Int, offset: Int) {
        if (artistsResource.value == null || query != artist || this.offset != offset) {
            query = artist
            this.limit = limit
            this.offset = offset
            searchArtists()
        }
    }

    // retry when error
    fun searchArtists() {
        if (query != "" && limit > 0) {
            artistSearchRepository.search(query, limit, offset)
        }
    }

    override fun onCleared() {
        super.onCleared()
        artistSearchRepository.cancelJob()
    }

}