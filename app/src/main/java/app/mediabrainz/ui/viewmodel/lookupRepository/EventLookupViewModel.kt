package app.mediabrainz.ui.viewmodel.lookupRepository

import app.mediabrainz.domain.model.Event
import app.mediabrainz.domain.repository.lookupRepository.EventLookupRepository


class EventLookupViewModel(val repo: EventLookupRepository = EventLookupRepository()) :
    BaseLookupViewModel<Event>(repo)
