package app.mediabrainz.domain.repository.lookupRepository

import androidx.lifecycle.MutableLiveData
import app.mediabrainz.api.ApiRequestProvider
import app.mediabrainz.domain.mapper.WorkMapper
import app.mediabrainz.domain.model.Work
import app.mediabrainz.domain.repository.BaseApiRepository
import app.mediabrainz.domain.repository.Resource


class WorkLookupRepository : BaseApiRepository() {

    fun lookup(mutableLiveData: MutableLiveData<Resource<Work>>, mbid: String) {
        call(mutableLiveData,
            { ApiRequestProvider.createWorkLookupRequest(mbid).lookup() },
            { WorkMapper().mapTo(this) })
    }

}