package app.mediabrainz.ui.viewmodel.searchRepository

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.mediabrainz.domain.model.Area
import app.mediabrainz.domain.repository.searchRepository.AreaSearchRepository
import app.mediabrainz.domain.repository.Resource


class AreaSearchViewModel : ViewModel() {

    private val repository = AreaSearchRepository()
    val result: MutableLiveData<Resource<List<Area>>> = MutableLiveData()

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