package app.mediabrainz.ui.viewmodel.lookupRepository

import app.mediabrainz.domain.model.Event
import app.mediabrainz.domain.repository.lookupRepository.EventLookupRepository


class EventLookupViewModel : BaseLookupViewModel<Event>() {

    private val repository = EventLookupRepository()

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