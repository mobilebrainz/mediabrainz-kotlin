package app.mediabrainz.ui.viewmodel.lookupRepository

import app.mediabrainz.domain.model.Place
import app.mediabrainz.domain.repository.lookupRepository.PlaceLookupRepository


class PlaceLookupViewModel : BaseLookupViewModel<Place>() {

    private val repository = PlaceLookupRepository()

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