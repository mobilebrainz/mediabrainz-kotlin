package app.mediabrainz.ui.viewmodel.lookupRepository

import app.mediabrainz.domain.model.Label
import app.mediabrainz.domain.repository.lookupRepository.LabelLookupRepository


class LabelLookupViewModel : BaseLookupViewModel<Label>() {

    private val repository = LabelLookupRepository()

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