package app.mediabrainz.ui.viewmodel

import androidx.lifecycle.ViewModel
import app.mediabrainz.domain.repository.CDStubSearchRepository


class CDStubSearchViewModel : ViewModel() {

    private val cdstubSearchRepository = CDStubSearchRepository()
    val cdstubResource = cdstubSearchRepository.mutableLiveData

    private var query: String = ""
    private var limit: Int = 0
    private var offset: Int = 0

    fun searchCDStub(artist: String, limit: Int, offset: Int) {
        if (cdstubResource.value == null || query != artist || this.offset != offset) {
            query = artist
            this.limit = limit
            this.offset = offset
            searchCDStub()
        }
    }

    // retry when error
    fun searchCDStub() {
        if (query != "" && limit > 0) {
            cdstubSearchRepository.search(query, limit, offset)
        }
    }

    override fun onCleared() {
        super.onCleared()
        cdstubSearchRepository.cancelJob()
    }

}