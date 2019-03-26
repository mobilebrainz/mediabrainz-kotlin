package app.mediabrainz.domain.repository.lookupRepository

import androidx.lifecycle.MutableLiveData
import app.mediabrainz.api.ApiRequestProvider
import app.mediabrainz.domain.mapper.ISRCMapper
import app.mediabrainz.domain.model.ISRC
import app.mediabrainz.domain.repository.BaseApiRepository
import app.mediabrainz.domain.repository.Resource


class ISRCLookupRepository : BaseLookupRepository<ISRC>() {

    override fun lookup(mutableLiveData: MutableLiveData<Resource<ISRC>>, mbid: String) {
        if (mbid.isNotBlank()) {
            call(mutableLiveData,
                { ApiRequestProvider.createISRCLookupRequest(mbid).lookup() },
                { ISRCMapper().mapTo(this) })
        }
    }

}