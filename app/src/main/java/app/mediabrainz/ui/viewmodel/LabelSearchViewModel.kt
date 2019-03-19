package app.mediabrainz.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.mediabrainz.domain.model.Label
import app.mediabrainz.domain.repository.LabelSearchRepository
import app.mediabrainz.domain.repository.Resource


class LabelSearchViewModel : ViewModel() {

    private val labelSearchRepository = LabelSearchRepository()
    val labelResource: MutableLiveData<Resource<List<Label>>> = MutableLiveData()

    private var query: String = ""

    fun searchLabel(query: String) {
        if (labelResource.value != null || this.query != query) {
            this.query = query
            searchLabel()
        }
    }

    // retry when error
    fun searchLabel() {
        if (query != "") {
            labelSearchRepository.search(labelResource, query)
        }
    }

    override fun onCleared() {
        super.onCleared()
        labelSearchRepository.cancelJob()
    }

}