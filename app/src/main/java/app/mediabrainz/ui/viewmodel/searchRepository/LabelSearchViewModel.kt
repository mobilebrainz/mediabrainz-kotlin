package app.mediabrainz.ui.viewmodel.searchRepository

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.mediabrainz.domain.model.Label
import app.mediabrainz.domain.repository.searchRepository.LabelSearchRepository
import app.mediabrainz.domain.repository.Resource


class LabelSearchViewModel : ViewModel() {

    private val repository = LabelSearchRepository()
    val result: MutableLiveData<Resource<List<Label>>> = MutableLiveData()

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