package app.mediabrainz.ui.viewmodel.lookupRepository

import app.mediabrainz.domain.model.Instrument
import app.mediabrainz.domain.repository.lookupRepository.InstrumentLookupRepository


class InstrumentLookupViewModel(val repo: InstrumentLookupRepository = InstrumentLookupRepository()) :
    BaseLookupViewModel<Instrument>(repo)
