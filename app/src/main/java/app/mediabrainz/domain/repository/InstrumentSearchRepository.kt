package app.mediabrainz.domain.repository

import app.mediabrainz.api.ApiRequestProvider
import app.mediabrainz.api.response.InstrumentSearchResponse
import app.mediabrainz.domain.mapper.InstrumentMapper
import app.mediabrainz.domain.model.Instrument
import app.mediabrainz.domain.parenthesesString


class InstrumentSearchRepository : BaseApiRepository<InstrumentSearchResponse, List<Instrument>>() {

    fun search(query: String, limit: Int, offset: Int) {
        mutableLiveData.value = Resource.loading()
        recursiveSearch(query, limit, offset)
    }

    private fun recursiveSearch(query: String, limit: Int, offset: Int) {
        val request = ApiRequestProvider.createInstrumentSearchRequest()
            .search(parenthesesString(query), limit, offset)
        call(request,
            { recursiveSearch(query, limit, offset) },
            { InstrumentMapper().mapToList(instruments) })
    }

}