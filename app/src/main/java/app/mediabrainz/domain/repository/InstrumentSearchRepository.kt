package app.mediabrainz.domain.repository

import androidx.lifecycle.MutableLiveData
import app.mediabrainz.api.ApiRequestProvider
import app.mediabrainz.api.response.InstrumentResponse
import app.mediabrainz.domain.mapper.InstrumentMapper
import app.mediabrainz.domain.mapper.PageMapper
import app.mediabrainz.domain.model.Instrument
import app.mediabrainz.domain.parenthesesString


class InstrumentSearchRepository : BaseApiRepository() {

    fun search(mutableLiveData: MutableLiveData<Resource<List<Instrument>>>, query: String) {
        val limit = 100
        call(mutableLiveData,
            {
                ApiRequestProvider.createInstrumentSearchRequest()
                    .search(parenthesesString(query), limit, 0)
            },
            {
                PageMapper<InstrumentResponse, Instrument> { InstrumentMapper().mapTo(it) }.mapToList(getItems())
            }
        )
    }

}
