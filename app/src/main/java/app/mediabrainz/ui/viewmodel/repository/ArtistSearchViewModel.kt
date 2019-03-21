package app.mediabrainz.ui.viewmodel.repository

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.mediabrainz.domain.model.Artist
import app.mediabrainz.domain.repository.ArtistSearchRepository
import app.mediabrainz.domain.repository.Resource


class ArtistSearchViewModel : ViewModel() {

    private val artistSearchRepository = ArtistSearchRepository()
    val artistsResource: MutableLiveData<Resource<List<Artist>>> = MutableLiveData()

    private var query: String = ""

    fun searchArtist(query: String) {
        if (artistsResource.value != null || this.query != query) {
            this.query = query
            searchArtist()
        }
    }

    // retry when error
    fun searchArtist() {
        if (query != "") {
            artistSearchRepository.search(artistsResource, query)
        }
    }

    override fun onCleared() {
        super.onCleared()
        artistSearchRepository.cancelJob()
    }

}