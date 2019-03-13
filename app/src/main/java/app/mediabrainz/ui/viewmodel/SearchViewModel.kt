package app.mediabrainz.ui.viewmodel

import androidx.lifecycle.ViewModel
import app.mediabrainz.domain.repository.AnnotationSearchRepository
import app.mediabrainz.domain.repository.ArtistSearchRepository


class SearchViewModel : ViewModel() {

    private val artistSearchRepository = ArtistSearchRepository()
    val artistsResource = artistSearchRepository.mutableLiveData

    private val annotationSearchRepository = AnnotationSearchRepository()
    val annotationsResource = annotationSearchRepository.mutableLiveData

    private var query: String = ""
    private var limit: Int = 0
    private var offset: Int = 0

    fun fetchArtists(artist: String, limit: Int, offset: Int) {
        if (artistsResource.value == null || query != artist || this.offset != offset) {
            query = artist
            this.limit = limit
            this.offset = offset
            loadArtists()
        }
    }

    // retry when error
    fun loadArtists() {
        if (query != "" && limit > 0) {
            artistSearchRepository.search(query, limit, offset)
        }
    }

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
        artistSearchRepository.cancelJob()
        annotationSearchRepository.cancelJob()
    }

}