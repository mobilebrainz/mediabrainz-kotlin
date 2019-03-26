package app.mediabrainz.domain.repository.lookupRepository

import androidx.lifecycle.MutableLiveData
import app.mediabrainz.api.ApiRequestProvider
import app.mediabrainz.domain.mapper.DiscMapper
import app.mediabrainz.domain.model.Disc
import app.mediabrainz.domain.repository.BaseApiRepository
import app.mediabrainz.domain.repository.Resource


class DiscidLookupRepository : BaseLookupRepository<Disc>() {

    override fun lookup(mutableLiveData: MutableLiveData<Resource<Disc>>, mbid: String) {
        if (mbid.isNotBlank()) {
            call(mutableLiveData,
                { ApiRequestProvider.createDiscidLookupRequest(mbid).lookup() },
                { DiscMapper().mapTo(this) })
        }
    }

}