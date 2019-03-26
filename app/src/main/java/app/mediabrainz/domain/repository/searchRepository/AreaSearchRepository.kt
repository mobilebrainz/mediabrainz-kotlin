package app.mediabrainz.domain.repository.searchRepository

import androidx.lifecycle.MutableLiveData
import app.mediabrainz.api.ApiRequestProvider
import app.mediabrainz.api.response.AreaResponse
import app.mediabrainz.domain.mapper.AreaMapper
import app.mediabrainz.domain.mapper.PageMapper
import app.mediabrainz.domain.model.Area
import app.mediabrainz.domain.parenthesesString
import app.mediabrainz.domain.repository.BaseApiRepository
import app.mediabrainz.domain.repository.Resource


class AreaSearchRepository : BaseSearchRepository<Area>() {

    override fun search(mutableLiveData: MutableLiveData<Resource<List<Area>>>, query: String) {
        if (query.isNotBlank()) {
            val limit = 100
            call(mutableLiveData,
                {
                    ApiRequestProvider.createAreaSearchRequest()
                        .search(parenthesesString(query), limit, 0)
                },
                {
                    PageMapper<AreaResponse, Area> { AreaMapper().mapTo(it) }.mapToList(getItems())
                }
            )
        }
    }

}