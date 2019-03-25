package app.mediabrainz.ui.viewmodel.searchRepository

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.mediabrainz.domain.model.Artist
import app.mediabrainz.domain.repository.searchRepository.ArtistSearchRepository
import app.mediabrainz.domain.repository.Resource


class ArtistSearchViewModel : ViewModel() {

    private val repository = ArtistSearchRepository()
    val result: MutableLiveData<Resource<List<Artist>>> = MutableLiveData()

    private var query: String = ""

    fun search(query: String) {
        if (result.value == null || this.query != query) {
            this.query = query
            search()
        }
    }

    // retry when error
    fun search() {
        if (query != "") {
            repository.search(result, query)
        }
    }

    override fun onCleared() {
        super.onCleared()
        repository.cancelJob()
    }

}