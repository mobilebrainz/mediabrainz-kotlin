package app.mediabrainz.domain.repository.lookupRepository

import androidx.lifecycle.MutableLiveData
import app.mediabrainz.api.ApiRequestProvider
import app.mediabrainz.domain.mapper.ISWCMapper
import app.mediabrainz.domain.model.ISWC
import app.mediabrainz.domain.repository.BaseApiRepository
import app.mediabrainz.domain.repository.Resource


class ISWCLookupRepository : BaseApiRepository() {

    fun lookup(mutableLiveData: MutableLiveData<Resource<ISWC>>, mbid: String) {
        call(mutableLiveData,
            { ApiRequestProvider.createISWCLookupRequest(mbid).lookup() },
            { ISWCMapper().mapTo(this) })
    }

}