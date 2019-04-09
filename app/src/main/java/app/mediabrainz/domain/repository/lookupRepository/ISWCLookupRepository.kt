package app.mediabrainz.domain.repository.lookupRepository

import androidx.lifecycle.MutableLiveData
import app.mediabrainz.api.ApiRequestProvider
import app.mediabrainz.domain.mapper.ISWCMapper
import app.mediabrainz.domain.model.ISWC
import app.mediabrainz.domain.repository.Resource


class ISWCLookupRepository : BaseLookupRepository<ISWC>() {

    override fun lookup(mutableLiveData: MutableLiveData<Resource<ISWC>>, mbid: String) {
        if (mbid.isNotBlank()) {
            call(
                mutableLiveData,
                { ApiRequestProvider.createISWCLookupRequest(mbid).lookup() },
                { ISWCMapper().mapTo(this) },
                false
            )
        }
    }

}