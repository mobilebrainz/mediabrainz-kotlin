package app.mediabrainz.ui.viewmodel.searchRepository

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.mediabrainz.domain.model.Event
import app.mediabrainz.domain.repository.searchRepository.EventSearchRepository
import app.mediabrainz.domain.repository.Resource


class EventSearchViewModel : ViewModel() {

    private val repository = EventSearchRepository()
    val result: MutableLiveData<Resource<List<Event>>> = MutableLiveData()

    private var query: String = ""

    fun search(query: String) {
        if (result.value == null || this.query != query) {
            this.query = query
            search()
        }
    }

    // retry when error
    fun search() {
        if (query != "") {
            repository.search(result, query)
        }
    }

    override fun onCleared() {
        super.onCleared()
        repository.cancelJob()
    }

}