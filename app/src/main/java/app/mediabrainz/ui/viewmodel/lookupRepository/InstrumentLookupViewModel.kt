package app.mediabrainz.ui.viewmodel.lookupRepository

import app.mediabrainz.domain.model.Instrument
import app.mediabrainz.domain.repository.lookupRepository.InstrumentLookupRepository


class InstrumentLookupViewModel : BaseLookupViewModel<Instrument>() {

    private val repository = InstrumentLookupRepository()

    // retry when error
    override fun lookup() {
        if (mbid != "") {
            repository.lookup(result, mbid)
        }
    }

    override fun onCleared() {
        super.onCleared()
        repository.cancelJob()
    }

}