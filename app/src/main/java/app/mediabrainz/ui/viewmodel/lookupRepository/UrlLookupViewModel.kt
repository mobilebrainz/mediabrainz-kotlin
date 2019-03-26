package app.mediabrainz.ui.viewmodel.lookupRepository

import app.mediabrainz.domain.model.Url
import app.mediabrainz.domain.repository.lookupRepository.UrlLookupRepository


class UrlLookupViewModel(val repo: UrlLookupRepository = UrlLookupRepository()) :
    BaseLookupViewModel<Url>(repo) {

    override fun lookup() {
        repo.lookup(result, mbid)
    }

}