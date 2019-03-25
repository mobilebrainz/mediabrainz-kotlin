package app.mediabrainz.ui.viewmodel.searchRepository

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.mediabrainz.domain.model.Url
import app.mediabrainz.domain.repository.Resource
import app.mediabrainz.domain.repository.searchRepository.UrlSearchRepository


class UrlSearchViewModel : ViewModel() {

    private val repository = UrlSearchRepository()
    val result: MutableLiveData<Resource<List<Url>>> = MutableLiveData()

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