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

    fun searchReleaseGroup(rg: String, limit: Int, offset: Int) {
        searchReleaseGroup("", rg, limit, offset)
    }

    fun searchReleaseGroup(artist: String, rg: String, limit: Int, offset: Int) {
        if (releaseGroupsResource.value == null
            || artistQuery != artist || rgQuery != rg || this.offset != offset
        ) {
            this.artistQuery = artist
            this.rgQuery = rg
            this.limit = limit
            this.offset = offset
            searchReleaseGroup()
        }
    }

    // retry when error
    fun searchReleaseGroup() {
        if (rgQuery != "" && limit > 0) {
            rgSearchRepository.search(artistQuery, rgQuery, limit, offset)
        }
    }

    override fun onCleared() {
        super.onCleared()
        rgSearchRepository.cancelJob()
    }

}