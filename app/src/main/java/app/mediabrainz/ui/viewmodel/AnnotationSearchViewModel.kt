package app.mediabrainz.ui.viewmodel

import androidx.lifecycle.ViewModel
import app.mediabrainz.domain.repository.AnnotationSearchRepository
import app.mediabrainz.domain.repository.ArtistSearchRepository


class AnnotationSearchViewModel : ViewModel() {

    private val annotationSearchRepository = AnnotationSearchRepository()
    val annotationsResource = annotationSearchRepository.mutableLiveData

    private var query: String = ""
    private var limit: Int = 0
    private var offset: Int = 0

    fun fetchAnnotations(query: String, limit: Int, offset: Int) {
        if (annotationsResource.value == null || this.query != query || this.offset != offset) {
            this.query = query
            this.limit = limit
            this.offset = offset
            loadAnnotations()
        }
    }

    // retry when error
    fun loadAnnotations() {
        if (query != "" && limit > 0) {
            annotationSearchRepository.search(query, limit, offset)
        }
    }

    override fun onCleared() {
        super.onCleared()
        annotationSearchRepository.cancelJob()
    }

}