package app.mediabrainz.domain.repository.lookupRepository

import androidx.lifecycle.MutableLiveData
import app.mediabrainz.api.ApiRequestProvider
import app.mediabrainz.domain.mapper.InstrumentMapper
import app.mediabrainz.domain.model.Instrument
import app.mediabrainz.domain.repository.BaseApiRepository
import app.mediabrainz.domain.repository.Resource


class InstrumentLookupRepository : BaseLookupRepository<Instrument>() {

    override fun lookup(mutableLiveData: MutableLiveData<Resource<Instrument>>, mbid: String) {
        if (mbid.isNotBlank()) {
            call(mutableLiveData,
                { ApiRequestProvider.createInstrumentLookupRequest(mbid).lookup() },
                { InstrumentMapper().mapTo(this) })
        }
    }

}