package app.mediabrainz.ui.viewmodel.lookupRepository

import app.mediabrainz.domain.model.Artist
import app.mediabrainz.domain.repository.lookupRepository.ArtistLookupRepository


class ArtistLookupViewModel : BaseLookupViewModel<Artist>() {

    private val repository = ArtistLookupRepository()

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