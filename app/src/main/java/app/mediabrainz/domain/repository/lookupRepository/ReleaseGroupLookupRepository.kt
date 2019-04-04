package app.mediabrainz.domain.repository.lookupRepository

import androidx.lifecycle.MutableLiveData
import app.mediabrainz.api.ApiRequestProvider
import app.mediabrainz.api.lookuprequest.ReleaseGroupLookupIncType.*
import app.mediabrainz.domain.OAuthManager
import app.mediabrainz.domain.mapper.ReleaseGroupMapper
import app.mediabrainz.domain.model.ReleaseGroup
import app.mediabrainz.domain.repository.Resource


class ReleaseGroupLookupRepository : BaseLookupRepository<ReleaseGroup>() {

    override fun lookup(mutableLiveData: MutableLiveData<Resource<ReleaseGroup>>, mbid: String) {
        if (mbid.isNotBlank()) {
            call(mutableLiveData,
                { ApiRequestProvider.createReleaseGroupLookupRequest(mbid).lookup() },
                { ReleaseGroupMapper().mapTo(this) })
        }
    }

    override fun authLookup(mutableLiveData: MutableLiveData<Resource<ReleaseGroup>>, mbid: String) {
        if (mbid.isNotBlank()) {
            call(mutableLiveData,
                {
                    ApiRequestProvider.createReleaseGroupLookupRequest(mbid)
                        .addIncs(USER_TAGS, USER_GENRES, USER_RATINGS)
                        .addAccessToken(OAuthManager.accessToken?.token ?: "")
                        .lookup()
                },
                { ReleaseGroupMapper().mapTo(this) })
        }
    }

}