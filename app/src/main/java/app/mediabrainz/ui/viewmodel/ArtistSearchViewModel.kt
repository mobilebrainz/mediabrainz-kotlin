package app.mediabrainz.ui.viewmodel

import androidx.lifecycle.ViewModel
import app.mediabrainz.domain.repository.ArtistSearchRepository


class ArtistSearchViewModel : ViewModel() {

    private val artistSearchRepository = ArtistSearchRepository()
    val artistsResource = artistSearchRepository.mutableLiveData

    private var query: String = ""
    private var limit: Int = 0
    private var offset: Int = 0

    fun searchArtist(query: String, limit: Int, offset: Int) {
        if (artistsResource.value == null || this.query != query || this.offset != offset) {
            this.query = query
            this.limit = limit
            this.offset = offset
            searchArtist()
        }
    }

    // retry when error
    fun searchArtist() {
        if (query != "" && limit > 0) {
            artistSearchRepository.search(query, limit, offset)
        }
    }

    override fun onCleared() {
        super.onCleared()
        artistSearchRepository.cancelJob()
    }

}