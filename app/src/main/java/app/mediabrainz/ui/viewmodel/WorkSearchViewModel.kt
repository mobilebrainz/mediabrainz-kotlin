package app.mediabrainz.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.mediabrainz.domain.model.Work
import app.mediabrainz.domain.repository.Resource
import app.mediabrainz.domain.repository.WorkSearchRepository


class WorkSearchViewModel : ViewModel() {

    private val workSearchRepository = WorkSearchRepository()
    val workResource: MutableLiveData<Resource<List<Work>>> = MutableLiveData()

    private var query: String = ""

    fun searchWork(query: String) {
        if (workResource.value != null || this.query != query) {
            this.query = query
            searchWork()
        }
    }

    // retry when error
    fun searchWork() {
        if (query != "") {
            workSearchRepository.search(workResource, query)
        }
    }

    override fun onCleared() {
        super.onCleared()
        workSearchRepository.cancelJob()
    }

}