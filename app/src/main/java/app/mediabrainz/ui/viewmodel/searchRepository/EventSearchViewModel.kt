package app.mediabrainz.ui.viewmodel.searchRepository

import app.mediabrainz.domain.model.Event
import app.mediabrainz.domain.repository.searchRepository.EventSearchRepository


class EventSearchViewModel(val repo: EventSearchRepository = EventSearchRepository()) :
    BaseSearchViewModel<Event>(repo) {

    override fun search() {
        repo.search(result, query)
    }
}