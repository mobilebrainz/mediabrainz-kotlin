package app.mediabrainz.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.mediabrainz.domain.model.Annotation
import app.mediabrainz.domain.repository.AnnotationSearchRepository
import app.mediabrainz.domain.repository.Resource


class AnnotationSearchViewModel : ViewModel() {

    private val annotationSearchRepository = AnnotationSearchRepository()
    val annotationResource: MutableLiveData<Resource<List<Annotation>>> = MutableLiveData()

    private var query: String = ""

    fun searchArtist(query: String) {
        if (annotationResource.value != null || this.query != query) {
            this.query = query
            searchAnnotation()
        }
    }

    // retry when error
    fun searchAnnotation() {
        if (query != "") {
            annotationSearchRepository.search(annotationResource, query)
        }
    }

    override fun onCleared() {
        super.onCleared()
        annotationSearchRepository.cancelJob()
    }

}