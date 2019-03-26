package app.mediabrainz.ui.viewmodel.lookupRepository

import app.mediabrainz.domain.model.Release
import app.mediabrainz.domain.repository.lookupRepository.ReleaseLookupRepository


class ReleaseLookupViewModel(val repo: ReleaseLookupRepository = ReleaseLookupRepository()) :
    BaseLookupViewModel<Release>(repo)
