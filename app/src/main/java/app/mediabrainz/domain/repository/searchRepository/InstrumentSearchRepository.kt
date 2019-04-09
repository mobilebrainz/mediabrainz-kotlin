package app.mediabrainz.domain.repository.searchRepository

import androidx.lifecycle.MutableLiveData
import app.mediabrainz.api.ApiRequestProvider
import app.mediabrainz.api.response.InstrumentResponse
import app.mediabrainz.domain.mapper.InstrumentMapper
import app.mediabrainz.domain.mapper.PageMapper
import app.mediabrainz.domain.model.Instrument
import app.mediabrainz.domain.parenthesesString
import app.mediabrainz.domain.repository.BaseApiRepository
import app.mediabrainz.domain.repository.Resource


class InstrumentSearchRepository : BaseSearchRepository<Instrument>() {

    override fun search(mutableLiveData: MutableLiveData<Resource<List<Instrument>>>, query: String) {
        if (query.isNotBlank()) {
            val limit = 100
            call(mutableLiveData,
                {
                    ApiRequestProvider.createInstrumentSearchRequest()
                        .search(parenthesesString(query), limit, 0)
                },
                {
                    PageMapper<InstrumentResponse, Instrument> { InstrumentMapper().mapTo(it) }.mapToList(items)
                },
                false
            )
        }
    }

}
