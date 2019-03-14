package app.mediabrainz.domain.repository

import app.mediabrainz.api.ApiRequestProvider
import app.mediabrainz.api.response.AreaSearchResponse
import app.mediabrainz.domain.mapper.AreaMapper
import app.mediabrainz.domain.model.Area
import app.mediabrainz.domain.parenthesesString


class AreaSearchRepository : BaseApiRepository<AreaSearchResponse, List<Area>>() {

    fun search(query: String, limit: Int, offset: Int) {
        mutableLiveData.value = Resource.loading()
        recursiveSearch(query, limit, offset)
    }

    private fun recursiveSearch(query: String, limit: Int, offset: Int) {
        val request = ApiRequestProvider.createAreaSearchRequest()
            .search(parenthesesString(query), limit, offset)
        call(request,
            { recursiveSearch(query, limit, offset) },
            { AreaMapper().mapToList(areas) })
    }

}