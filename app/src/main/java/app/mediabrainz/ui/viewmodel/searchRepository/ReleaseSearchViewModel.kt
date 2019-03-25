package app.mediabrainz.ui.viewmodel.searchRepository

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.mediabrainz.domain.model.Release
import app.mediabrainz.domain.repository.searchRepository.ReleaseSearchRepository
import app.mediabrainz.domain.repository.Resource


class ReleaseSearchViewModel : ViewModel() {

    private val repository = ReleaseSearchRepository()
    val result: MutableLiveData<Resource<List<Release>>> = MutableLiveData()

    private var artistQuery: String = ""
    private var releaseQuery: String = ""

    fun search(release: String) {
        search("", release)
    }

    fun search(artist: String, release: String) {
        if (result.value == null || artistQuery != artist || releaseQuery != release) {
            this.artistQuery = artist
            this.releaseQuery = release
            search()
        }
    }

    // retry when error
    fun search() {
        if (releaseQuery != "") {
            repository.search(result, artistQuery, releaseQuery)
        }
    }

    override fun onCleared() {
        super.onCleared()
        repository.cancelJob()
    }

}