package app.mediabrainz.ui.viewmodel.lookupRepository

import app.mediabrainz.domain.model.Series
import app.mediabrainz.domain.repository.lookupRepository.SeriesLookupRepository


class SeriesLookupViewModel(val repo: SeriesLookupRepository = SeriesLookupRepository()) :
    BaseLookupViewModel<Series>(repo)
