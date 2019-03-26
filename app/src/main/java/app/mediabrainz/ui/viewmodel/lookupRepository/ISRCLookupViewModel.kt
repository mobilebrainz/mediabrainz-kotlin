package app.mediabrainz.ui.viewmodel.lookupRepository

import app.mediabrainz.domain.model.ISRC
import app.mediabrainz.domain.repository.lookupRepository.ISRCLookupRepository


class ISRCLookupViewModel(val repo: ISRCLookupRepository = ISRCLookupRepository()) :
    BaseLookupViewModel<ISRC>(repo) {

    override fun lookup() {
        repo.lookup(result, mbid)
    }

}