package app.mediabrainz.domain.repository.searchRepository

import androidx.lifecycle.MutableLiveData
import app.mediabrainz.api.ApiRequestProvider
import app.mediabrainz.api.response.AnnotationResponse
import app.mediabrainz.domain.mapper.AnnotationMapper
import app.mediabrainz.domain.mapper.PageMapper
import app.mediabrainz.domain.model.Annotation
import app.mediabrainz.domain.parenthesesString
import app.mediabrainz.domain.repository.BaseApiRepository
import app.mediabrainz.domain.repository.Resource


class AnnotationSearchRepository : BaseSearchRepository<Annotation>() {

    override fun search(mutableLiveData: MutableLiveData<Resource<List<Annotation>>>, query: String) {
        val limit = 100
        call(mutableLiveData,
            {
                ApiRequestProvider.createAnnotationSearchRequest()
                    .search(parenthesesString(query), limit, 0)
            },
            {
                PageMapper<AnnotationResponse, Annotation> { AnnotationMapper().mapTo(it) }.mapToList(items)
            }
        )
    }

}