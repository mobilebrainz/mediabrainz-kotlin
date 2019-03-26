package app.mediabrainz.ui.viewmodel.lookupRepository

import app.mediabrainz.domain.model.ReleaseGroup
import app.mediabrainz.domain.repository.lookupRepository.ReleaseGroupLookupRepository


class ReleaseGroupLookupViewModel(val repo: ReleaseGroupLookupRepository = ReleaseGroupLookupRepository()) :
    BaseLookupViewModel<ReleaseGroup>(repo)
