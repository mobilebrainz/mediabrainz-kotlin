package app.mediabrainz.ui.viewmodel.repository

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.mediabrainz.domain.model.Area
import app.mediabrainz.domain.repository.AreaSearchRepository
import app.mediabrainz.domain.repository.Resource


class AreaSearchViewModel : ViewModel() {

    private val areaSearchRepository = AreaSearchRepository()
    val areaResource: MutableLiveData<Resource<List<Area>>> = MutableLiveData()

    private var query: String = ""

    fun searchArea(query: String) {
        if (areaResource.value != null || this.query != query) {
            this.query = query
            searchArea()
        }
    }

    // retry when error
    fun searchArea() {
        if (query != "") {
            areaSearchRepository.search(areaResource, query)
        }
    }

    override fun onCleared() {
        super.onCleared()
        areaSearchRepository.cancelJob()
    }

}