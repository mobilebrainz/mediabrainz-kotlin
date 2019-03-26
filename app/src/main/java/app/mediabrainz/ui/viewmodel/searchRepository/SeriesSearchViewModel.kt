package app.mediabrainz.ui.viewmodel.searchRepository

import app.mediabrainz.domain.model.Series
import app.mediabrainz.domain.repository.searchRepository.SeriesSearchRepository


class SeriesSearchViewModel(val repo: SeriesSearchRepository = SeriesSearchRepository()) :
    BaseSearchViewModel<Series>(repo)
