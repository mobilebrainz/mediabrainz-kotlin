package app.mediabrainz.domain.repository.searchRepository

import androidx.lifecycle.MutableLiveData
import app.mediabrainz.api.ApiRequestProvider
import app.mediabrainz.api.response.WorkResponse
import app.mediabrainz.domain.mapper.PageMapper
import app.mediabrainz.domain.mapper.WorkMapper
import app.mediabrainz.domain.model.Work
import app.mediabrainz.domain.parenthesesString
import app.mediabrainz.domain.repository.BaseApiRepository
import app.mediabrainz.domain.repository.Resource


class WorkSearchRepository : BaseSearchRepository<Work>() {

    override fun search(mutableLiveData: MutableLiveData<Resource<List<Work>>>, query: String) {
        if (query.isNotBlank()) {
            val limit = 100
            call(mutableLiveData,
                {
                    ApiRequestProvider.createWorkSearchRequest()
                        .search(parenthesesString(query), limit, 0)
                },
                {
                    PageMapper<WorkResponse, Work> { WorkMapper().mapTo(it) }.mapToList(items)
                }
            )
        }
    }

}
