package app.mediabrainz.ui.viewmodel

import androidx.lifecycle.ViewModel
import app.mediabrainz.domain.repository.InstrumentSearchRepository


class InstrumentSearchViewModel : ViewModel() {

    private val instrumentSearchRepository = InstrumentSearchRepository()
    val instrumentsResource = instrumentSearchRepository.mutableLiveData

    private var query: String = ""
    private var limit: Int = 0
    private var offset: Int = 0

    fun searchInstrument(query: String, limit: Int, offset: Int) {
        if (instrumentsResource.value == null || this.query != query || this.offset != offset) {
            this.query = query
            this.limit = limit
            this.offset = offset
            searchInstrument()
        }
    }

    // retry when error
    fun searchInstrument() {
        if (query != "" && limit > 0) {
            instrumentSearchRepository.search(query, limit, offset)
        }
    }

    override fun onCleared() {
        super.onCleared()
        instrumentSearchRepository.cancelJob()
    }

}