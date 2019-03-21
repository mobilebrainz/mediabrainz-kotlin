package app.mediabrainz.ui.viewmodel.repository

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.mediabrainz.domain.model.ReleaseGroup
import app.mediabrainz.domain.repository.ReleaseGroupSearchRepository
import app.mediabrainz.domain.repository.Resource


class ReleaseGroupSearchViewModel : ViewModel() {

    private val rgSearchRepository = ReleaseGroupSearchRepository()
    val releaseGroupsResource: MutableLiveData<Resource<List<ReleaseGroup>>> = MutableLiveData()

    private var artistQuery: String = ""
    private var rgQuery: String = ""

    fun searchReleaseGroup(rg: String) {
        searchReleaseGroup("", rg)
    }

    fun searchReleaseGroup(artist: String, rg: String) {
        if (releaseGroupsResource.value == null || artistQuery != artist || rgQuery != rg) {
            this.artistQuery = artist
            this.rgQuery = rg
            searchReleaseGroup()
        }
    }

    // retry when error
    fun searchReleaseGroup() {
        if (rgQuery != "") {
            rgSearchRepository.search(releaseGroupsResource, artistQuery, rgQuery)
        }
    }

    override fun onCleared() {
        super.onCleared()
        rgSearchRepository.cancelJob()
    }

}