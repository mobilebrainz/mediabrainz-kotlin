package app.mediabrainz.ui.viewmodel.lookupRepository

import app.mediabrainz.domain.model.ISWC
import app.mediabrainz.domain.repository.lookupRepository.ISWCLookupRepository


class ISWCLookupViewModel(val repo: ISWCLookupRepository = ISWCLookupRepository()) :
    BaseLookupViewModel<ISWC>(repo) {

    override fun lookup() {
        repo.lookup(result, mbid)
    }

}