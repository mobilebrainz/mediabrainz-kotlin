package app.mediabrainz.ui.viewmodel.repository

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.mediabrainz.domain.model.Event
import app.mediabrainz.domain.repository.EventSearchRepository
import app.mediabrainz.domain.repository.Resource


class EventSearchViewModel : ViewModel() {

    private val eventSearchRepository = EventSearchRepository()
    val eventResource: MutableLiveData<Resource<List<Event>>> = MutableLiveData()

    private var query: String = ""

    fun searchEvent(query: String) {
        if (eventResource.value != null || this.query != query) {
            this.query = query
            searchEvent()
        }
    }

    // retry when error
    fun searchEvent() {
        if (query != "") {
            eventSearchRepository.search(eventResource, query)
        }
    }

    override fun onCleared() {
        super.onCleared()
        eventSearchRepository.cancelJob()
    }

}