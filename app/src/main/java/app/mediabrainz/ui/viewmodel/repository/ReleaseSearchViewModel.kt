package app.mediabrainz.ui.viewmodel.repository

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.mediabrainz.domain.model.Release
import app.mediabrainz.domain.repository.ReleaseSearchRepository
import app.mediabrainz.domain.repository.Resource


class ReleaseSearchViewModel : ViewModel() {

    private val releaseSearchRepository = ReleaseSearchRepository()
    val releaseResource: MutableLiveData<Resource<List<Release>>> = MutableLiveData()

    private var artistQuery: String = ""
    private var releaseQuery: String = ""

    fun searchRelease(release: String) {
        searchRelease("", release)
    }

    fun searchRelease(artist: String, release: String) {
        if (releaseResource.value == null || artistQuery != artist || releaseQuery != release) {
            this.artistQuery = artist
            this.releaseQuery = release
            searchRelease()
        }
    }

    // retry when error
    fun searchRelease() {
        if (releaseQuery != "") {
            releaseSearchRepository.search(releaseResource, artistQuery, releaseQuery)
        }
    }

    override fun onCleared() {
        super.onCleared()
        releaseSearchRepository.cancelJob()
    }

}