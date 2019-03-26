package app.mediabrainz.ui.viewmodel.searchRepository

import app.mediabrainz.domain.model.Instrument
import app.mediabrainz.domain.repository.searchRepository.InstrumentSearchRepository


class InstrumentSearchViewModel(val repo: InstrumentSearchRepository = InstrumentSearchRepository()) :
    BaseSearchViewModel<Instrument>(repo)