package app.mediabrainz.domain.repository.lookupRepository

import androidx.lifecycle.MutableLiveData
import app.mediabrainz.api.ApiRequestProvider
import app.mediabrainz.domain.mapper.ReleaseGroupMapper
import app.mediabrainz.domain.mapper.ReleaseMapper
import app.mediabrainz.domain.model.Release
import app.mediabrainz.domain.model.ReleaseGroup
import app.mediabrainz.domain.repository.BaseApiRepository
import app.mediabrainz.domain.repository.Resource


class ReleaseLookupRepository : BaseApiRepository() {

    fun lookup(mutableLiveData: MutableLiveData<Resource<Release>>, mbid: String) {
        call(mutableLiveData,
            { ApiRequestProvider.createReleaseLookupRequest(mbid).lookup() },
            { ReleaseMapper().mapTo(this) })
    }

}