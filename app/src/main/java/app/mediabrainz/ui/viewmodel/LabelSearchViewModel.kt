package app.mediabrainz.ui.viewmodel

import androidx.lifecycle.ViewModel
import app.mediabrainz.domain.repository.LabelSearchRepository


class LabelSearchViewModel : ViewModel() {

    private val labelSearchRepository = LabelSearchRepository()
    val labelsResource = labelSearchRepository.mutableLiveData

    private var query: String = ""
    private var limit: Int = 0
    private var offset: Int = 0

    fun searchLabel(query: String, limit: Int, offset: Int) {
        if (labelsResource.value == null || this.query != query || this.offset != offset) {
            this.query = query
            this.limit = limit
            this.offset = offset
            searchLabel()
        }
    }

    // retry when error
    fun searchLabel() {
        if (query != "" && limit > 0) {
            labelSearchRepository.search(query, limit, offset)
        }
    }

    override fun onCleared() {
        super.onCleared()
        labelSearchRepository.cancelJob()
    }

}