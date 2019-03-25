package app.mediabrainz.ui.viewmodel.searchRepository

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.mediabrainz.domain.model.ReleaseGroup
import app.mediabrainz.domain.repository.searchRepository.ReleaseGroupSearchRepository
import app.mediabrainz.domain.repository.Resource


class ReleaseGroupSearchViewModel : ViewModel() {

    private val repository = ReleaseGroupSearchRepository()
    val result: MutableLiveData<Resource<List<ReleaseGroup>>> = MutableLiveData()

    private var artistQuery: String = ""
    private var rgQuery: String = ""

    fun search(rg: String) {
        search("", rg)
    }

    fun search(artist: String, rg: String) {
        if (result.value == null || artistQuery != artist || rgQuery != rg) {
            this.artistQuery = artist
            this.rgQuery = rg
            search()
        }
    }

    // retry when error
    fun search() {
        if (rgQuery != "") {
            repository.search(result, artistQuery, rgQuery)
        }
    }

    override fun onCleared() {
        super.onCleared()
        repository.cancelJob()
    }

}