package app.mediabrainz.ui.viewmodel.lookupRepository

import app.mediabrainz.domain.model.Place
import app.mediabrainz.domain.repository.lookupRepository.PlaceLookupRepository


class PlaceLookupViewModel(val repo: PlaceLookupRepository = PlaceLookupRepository()) :
    BaseLookupViewModel<Place>(repo)
