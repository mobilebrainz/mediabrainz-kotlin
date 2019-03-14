package app.mediabrainz.ui.viewmodel

import androidx.lifecycle.ViewModel
import app.mediabrainz.domain.repository.EventSearchRepository


class EventSearchViewModel : ViewModel() {

    private val eventSearchRepository = EventSearchRepository()
    val eventsResource = eventSearchRepository.mutableLiveData

    private var query: String = ""
    private var limit: Int = 0
    private var offset: Int = 0

    fun searchEvent(query: String, limit: Int, offset: Int) {
        if (eventsResource.value == null || this.query != query || this.offset != offset) {
            this.query = query
            this.limit = limit
            this.offset = offset
            searchEvent()
        }
    }

    // retry when error
    fun searchEvent() {
        if (query != "" && limit > 0) {
            eventSearchRepository.search(query, limit, offset)
        }
    }

    override fun onCleared() {
        super.onCleared()
        eventSearchRepository.cancelJob()
    }

}