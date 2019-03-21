package app.mediabrainz.ui.viewmodel.repository

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.mediabrainz.domain.model.Instrument
import app.mediabrainz.domain.repository.InstrumentSearchRepository
import app.mediabrainz.domain.repository.Resource


class InstrumentSearchViewModel : ViewModel() {

    private val instrumentSearchRepository = InstrumentSearchRepository()
    val instrumentResource: MutableLiveData<Resource<List<Instrument>>> = MutableLiveData()

    private var query: String = ""

    fun searchInstrument(query: String) {
        if (instrumentResource.value != null || this.query != query) {
            this.query = query
            searchInstrument()
        }
    }

    // retry when error
    fun searchInstrument() {
        if (query != "") {
            instrumentSearchRepository.search(instrumentResource, query)
        }
    }

    override fun onCleared() {
        super.onCleared()
        instrumentSearchRepository.cancelJob()
    }

}