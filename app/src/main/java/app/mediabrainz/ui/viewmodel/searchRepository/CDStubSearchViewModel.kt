package app.mediabrainz.ui.viewmodel.searchRepository

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.mediabrainz.domain.model.CDStub
import app.mediabrainz.domain.repository.searchRepository.CDStubSearchRepository
import app.mediabrainz.domain.repository.Resource


class CDStubSearchViewModel : ViewModel() {

    private val repository = CDStubSearchRepository()
    val result: MutableLiveData<Resource<List<CDStub>>> = MutableLiveData()

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