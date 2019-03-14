package app.mediabrainz.ui.viewmodel

import androidx.lifecycle.ViewModel
import app.mediabrainz.domain.repository.AnnotationSearchRepository


class AnnotationSearchViewModel : ViewModel() {

    private val annotationSearchRepository = AnnotationSearchRepository()
    val annotationsResource = annotationSearchRepository.mutableLiveData

    private var query: String = ""
    private var limit: Int = 0
    private var offset: Int = 0

    fun searchAnnotations(query: String, limit: Int, offset: Int) {
        if (annotationsResource.value == null || this.query != query || this.offset != offset) {
            this.query = query
            this.limit = limit
            this.offset = offset
            searchAnnotations()
        }
    }

    // retry when error
    fun searchAnnotations() {
        if (query != "" && limit > 0) {
            annotationSearchRepository.search(query, limit, offset)
        }
    }

    override fun onCleared() {
        super.onCleared()
        annotationSearchRepository.cancelJob()
    }

}