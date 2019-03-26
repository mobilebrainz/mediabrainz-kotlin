package app.mediabrainz.ui.viewmodel.lookupRepository

import app.mediabrainz.domain.model.Recording
import app.mediabrainz.domain.repository.lookupRepository.RecordingLookupRepository


class RecordingLookupViewModel(val repo: RecordingLookupRepository = RecordingLookupRepository()) :
    BaseLookupViewModel<Recording>(repo) {

    override fun lookup() {
        repo.lookup(result, mbid)
    }

}