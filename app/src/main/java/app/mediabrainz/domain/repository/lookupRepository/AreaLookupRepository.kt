package app.mediabrainz.domain.repository.lookupRepository

import androidx.lifecycle.MutableLiveData
import app.mediabrainz.api.ApiRequestProvider
import app.mediabrainz.domain.mapper.AreaMapper
import app.mediabrainz.domain.model.Area
import app.mediabrainz.domain.repository.Resource


class AreaLookupRepository : BaseLookupRepository<Area>() {

    override fun lookup(mutableLiveData: MutableLiveData<Resource<Area>>, mbid: String) {
        if (mbid.isNotBlank()) {
            call(mutableLiveData,
                { ApiRequestProvider.createAreaLookupRequest(mbid).lookup() },
                { AreaMapper().mapTo(this) })
        }
    }

}