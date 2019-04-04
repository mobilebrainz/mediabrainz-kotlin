package app.mediabrainz.domain.repository.lookupRepository

import androidx.lifecycle.MutableLiveData
import app.mediabrainz.api.ApiRequestProvider
import app.mediabrainz.api.lookuprequest.PlaceLookupIncType
import app.mediabrainz.domain.OAuthManager
import app.mediabrainz.domain.mapper.PlaceMapper
import app.mediabrainz.domain.model.Place
import app.mediabrainz.domain.repository.Resource


class PlaceLookupRepository : BaseLookupRepository<Place>() {

    override fun lookup(mutableLiveData: MutableLiveData<Resource<Place>>, mbid: String) {
        if (mbid.isNotBlank()) {
            call(mutableLiveData,
                { ApiRequestProvider.createPlaceLookupRequest(mbid).lookup() },
                { PlaceMapper().mapTo(this) })
        }
    }

    override fun authLookup(mutableLiveData: MutableLiveData<Resource<Place>>, mbid: String) {
        if (mbid.isNotBlank()) {
            call(mutableLiveData,
                {
                    ApiRequestProvider.createPlaceLookupRequest(mbid)
                        .addIncs(PlaceLookupIncType.USER_TAGS)
                        .addAccessToken(OAuthManager.accessToken?.token ?: "")
                        .lookup()
                },
                { PlaceMapper().mapTo(this) })
        }
    }

}