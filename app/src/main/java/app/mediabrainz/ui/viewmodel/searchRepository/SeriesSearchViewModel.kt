package app.mediabrainz.ui.viewmodel.searchRepository

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.mediabrainz.domain.model.Series
import app.mediabrainz.domain.repository.Resource
import app.mediabrainz.domain.repository.searchRepository.SeriesSearchRepository


class SeriesSearchViewModel : ViewModel() {

    private val repository = SeriesSearchRepository()
    val result: MutableLiveData<Resource<List<Series>>> = MutableLiveData()

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