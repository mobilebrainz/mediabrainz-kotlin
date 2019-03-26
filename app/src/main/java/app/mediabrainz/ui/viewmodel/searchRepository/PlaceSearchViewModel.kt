package app.mediabrainz.ui.viewmodel.searchRepository

import app.mediabrainz.domain.model.Place
import app.mediabrainz.domain.repository.searchRepository.PlaceSearchRepository


class PlaceSearchViewModel(val repo: PlaceSearchRepository = PlaceSearchRepository()) :
    BaseSearchViewModel<Place>(repo)