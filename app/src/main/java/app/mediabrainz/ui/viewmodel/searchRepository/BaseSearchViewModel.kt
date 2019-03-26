package app.mediabrainz.ui.viewmodel.searchRepository

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.mediabrainz.domain.repository.BaseApiRepository
import app.mediabrainz.domain.repository.Resource


abstract class BaseSearchViewModel<T>(val repository: BaseApiRepository) : ViewModel() {

    val result: MutableLiveData<Resource<List<T>>> = MutableLiveData()

    protected var query: String = ""

    abstract fun search()

    open fun search(query: String) {
        if (result.value == null || this.query != query) {
            this.query = query
            search()
        }
    }

    override fun onCleared() {
        super.onCleared()
        repository.cancelJob()
    }

}