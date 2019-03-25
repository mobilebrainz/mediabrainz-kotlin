package app.mediabrainz.ui.viewmodel.searchRepository

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.mediabrainz.domain.model.Work
import app.mediabrainz.domain.repository.Resource
import app.mediabrainz.domain.repository.searchRepository.WorkSearchRepository


class WorkSearchViewModel : ViewModel() {

    private val repository = WorkSearchRepository()
    val result: MutableLiveData<Resource<List<Work>>> = MutableLiveData()

    private var query: String = ""

    fun searchWork(query: String) {
        if (result.value == null || this.query != query) {
            this.query = query
            searchWork()
        }
    }

    // retry when error
    fun searchWork() {
        if (query != "") {
            repository.search(result, query)
        }
    }

    override fun onCleared() {
        super.onCleared()
        repository.cancelJob()
    }

}