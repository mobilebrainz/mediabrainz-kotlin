package app.mediabrainz.domain.repository.lookupRepository

import androidx.lifecycle.MutableLiveData
import app.mediabrainz.api.ApiRequestProvider
import app.mediabrainz.api.lookuprequest.ReleaseLookupIncType.USER_GENRES
import app.mediabrainz.api.lookuprequest.ReleaseLookupIncType.USER_TAGS
import app.mediabrainz.domain.OAuthManager
import app.mediabrainz.domain.mapper.ReleaseMapper
import app.mediabrainz.domain.model.Release
import app.mediabrainz.domain.repository.Resource


class ReleaseLookupRepository : BaseLookupRepository<Release>() {

    override fun lookup(mutableLiveData: MutableLiveData<Resource<Release>>, mbid: String) {
        if (mbid.isNotBlank()) {
            call(mutableLiveData,
                { ApiRequestProvider.createReleaseLookupRequest(mbid).lookup() },
                { ReleaseMapper().mapTo(this) })
        }
    }

    override fun authLookup(mutableLiveData: MutableLiveData<Resource<Release>>, mbid: String) {
        if (mbid.isNotBlank()) {
            call(mutableLiveData,
                {
                    ApiRequestProvider.createReleaseLookupRequest(mbid)
                        .addIncs(USER_TAGS, USER_GENRES)
                        .addAccessToken(OAuthManager.accessToken?.token ?: "")
                        .lookup()
                },
                { ReleaseMapper().mapTo(this) })
        }
    }

}