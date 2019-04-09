package app.mediabrainz.domain.repository.lookupRepository

import androidx.lifecycle.MutableLiveData
import app.mediabrainz.api.ApiRequestProvider
import app.mediabrainz.domain.mapper.UrlMapper
import app.mediabrainz.domain.model.Url
import app.mediabrainz.domain.repository.Resource


class UrlLookupRepository : BaseLookupRepository<Url>() {

    override fun lookup(mutableLiveData: MutableLiveData<Resource<Url>>, mbid: String) {
        if (mbid.isNotBlank()) {
            call(
                mutableLiveData,
                { ApiRequestProvider.createUrlLookupRequest(mbid).lookup() },
                { UrlMapper().mapTo(this) },
                false
            )
        }
    }

}