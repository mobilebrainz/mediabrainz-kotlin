package app.mediabrainz.domain.repository

import app.mediabrainz.api.ApiRequestProvider
import app.mediabrainz.api.response.CDStubSearchResponse
import app.mediabrainz.domain.mapper.CDStubMapper
import app.mediabrainz.domain.model.CDStub
import app.mediabrainz.domain.parenthesesString


class CDStubSearchRepository : BaseApiRepository<CDStubSearchResponse, List<CDStub>>() {

    fun search(query: String, limit: Int, offset: Int) {
        mutableLiveData.value = Resource.loading()
        recursiveSearch(query, limit, offset)
    }

    private fun recursiveSearch(query: String, limit: Int, offset: Int) {
        val request = ApiRequestProvider.createCDStubSearchRequest()
            .search(parenthesesString(query), limit, offset)
        call(request,
            { recursiveSearch(query, limit, offset) },
            { CDStubMapper().mapToList(cdstubs) })
    }

}