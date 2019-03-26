package app.mediabrainz.ui.viewmodel.searchRepository

import app.mediabrainz.domain.model.Url
import app.mediabrainz.domain.repository.searchRepository.UrlSearchRepository


class UrlSearchViewModel(val repo: UrlSearchRepository = UrlSearchRepository()) :
    BaseSearchViewModel<Url>(repo)
