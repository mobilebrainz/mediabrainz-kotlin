package app.mediabrainz.domain.repository

import app.mediabrainz.api.ApiRequestProvider
import app.mediabrainz.api.response.AnnotationSearchResponse
import app.mediabrainz.domain.mapper.AnnotationMapper
import app.mediabrainz.domain.model.Annotation


class AnnotationSearchRepository : BaseApiRepository<AnnotationSearchResponse, List<Annotation>>() {

    fun search(query: String, limit: Int, offset: Int) {
        mutableLiveData.value = Resource.loading(null)
        recursiveSearch(query, limit, offset)
    }

    private fun recursiveSearch(query: String, limit: Int, offset: Int) {
        val request = ApiRequestProvider.createAnnotationSearchRequest().search(query, limit, offset)
        call(request,
            { recursiveSearch(query, limit, offset) },
            { AnnotationMapper().mapToList(annotations) })
    }

}