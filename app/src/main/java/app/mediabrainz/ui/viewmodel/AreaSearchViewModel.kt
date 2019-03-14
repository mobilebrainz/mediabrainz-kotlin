package app.mediabrainz.ui.viewmodel

import androidx.lifecycle.ViewModel
import app.mediabrainz.domain.repository.AreaSearchRepository


class AreaSearchViewModel : ViewModel() {

    private val areaSearchRepository = AreaSearchRepository()
    val areasResource = areaSearchRepository.mutableLiveData

    private var query: String = ""
    private var limit: Int = 0
    private var offset: Int = 0

    fun searchArea(artist: String, limit: Int, offset: Int) {
        if (areasResource.value == null || query != artist || this.offset != offset) {
            query = artist
            this.limit = limit
            this.offset = offset
            searchArea()
        }
    }

    // retry when error
    fun searchArea() {
        if (query != "" && limit > 0) {
            areaSearchRepository.search(query, limit, offset)
        }
    }

    override fun onCleared() {
        super.onCleared()
        areaSearchRepository.cancelJob()
    }

}