package app.mediabrainz.ui.viewmodel.lookupRepository

import app.mediabrainz.domain.model.Series
import app.mediabrainz.domain.repository.lookupRepository.SeriesLookupRepository


class SeriesLookupViewModel : BaseLookupViewModel<Series>() {

    private val repository = SeriesLookupRepository()

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