package app.mediabrainz.ui.viewmodel

import androidx.lifecycle.ViewModel
import app.mediabrainz.domain.repository.ReleaseGroupSearchRepository


class ReleaseGroupSearchViewModel : ViewModel() {

    private val rgSearchRepository = ReleaseGroupSearchRepository()
    val releaseGroupsResource = rgSearchRepository.mutableLiveData

    private var artistQuery: String = ""
    private var rgQuery: String = ""
    private var limit: Int = 0
    private var offset: Int = 0

    fun fetchReleaseGroups(rg: String, limit: Int, offset: Int) {
        fetchReleaseGroups("", rg, limit, offset)
    }

    fun fetchReleaseGroups(artist: String, rg: String, limit: Int, offset: Int) {
        if (releaseGroupsResource.value == null
            || artistQuery != artist || rgQuery != rg || this.offset != offset
        ) {
            artistQuery = artist
            rgQuery = rg
            this.limit = limit
            this.offset = offset
            loadReleaseGroups()
        }
    }

    // retry when error
    fun loadReleaseGroups() {
        if (rgQuery != "" && limit > 0) {
            rgSearchRepository.search(artistQuery, rgQuery, limit, offset)
        }
    }

    override fun onCleared() {
        super.onCleared()
        rgSearchRepository.cancelJob()
    }

}