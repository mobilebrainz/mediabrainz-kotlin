package app.mediabrainz.domain.repository.lookupRepository

import androidx.lifecycle.MutableLiveData
import app.mediabrainz.api.ApiRequestProvider
import app.mediabrainz.domain.mapper.PlaceMapper
import app.mediabrainz.domain.model.Place
import app.mediabrainz.domain.repository.BaseApiRepository
import app.mediabrainz.domain.repository.Resource


class PlaceLookupRepository : BaseApiRepository() {

    fun lookup(mutableLiveData: MutableLiveData<Resource<Place>>, mbid: String) {
        call(mutableLiveData,
            { ApiRequestProvider.createPlaceLookupRequest(mbid).lookup() },
            { PlaceMapper().mapTo(this) })
    }

}