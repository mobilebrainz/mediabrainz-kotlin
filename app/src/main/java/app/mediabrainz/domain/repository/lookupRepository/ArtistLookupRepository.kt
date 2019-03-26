package app.mediabrainz.domain.repository.lookupRepository

import androidx.lifecycle.MutableLiveData
import app.mediabrainz.api.ApiRequestProvider
import app.mediabrainz.domain.mapper.ArtistMapper
import app.mediabrainz.domain.model.Artist
import app.mediabrainz.domain.repository.BaseApiRepository
import app.mediabrainz.domain.repository.Resource


class ArtistLookupRepository : BaseLookupRepository<Artist>() {

    override fun lookup(mutableLiveData: MutableLiveData<Resource<Artist>>, mbid: String) {
        if (mbid.isNotBlank()) {
            call(mutableLiveData,
                { ApiRequestProvider.createArtistLookupRequest(mbid).lookup() },
                { ArtistMapper().mapTo(this) })
        }
    }

}