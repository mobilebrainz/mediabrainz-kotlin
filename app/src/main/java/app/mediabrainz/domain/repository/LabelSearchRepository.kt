package app.mediabrainz.domain.repository

import app.mediabrainz.api.ApiRequestProvider
import app.mediabrainz.api.response.LabelSearchResponse
import app.mediabrainz.domain.mapper.LabelMapper
import app.mediabrainz.domain.model.Label
import app.mediabrainz.domain.parenthesesString


class LabelSearchRepository : BaseApiRepository<LabelSearchResponse, List<Label>>() {

    fun search(query: String, limit: Int, offset: Int) {
        mutableLiveData.value = Resource.loading()
        recursiveSearch(query, limit, offset)
    }

    private fun recursiveSearch(query: String, limit: Int, offset: Int) {
        val request = ApiRequestProvider.createLabelSearchRequest()
            .search(parenthesesString(query), limit, offset)
        call(request,
            { recursiveSearch(query, limit, offset) },
            { LabelMapper().mapToList(labels) })
    }

}