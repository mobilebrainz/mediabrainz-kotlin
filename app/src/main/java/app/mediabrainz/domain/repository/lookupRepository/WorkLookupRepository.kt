package app.mediabrainz.domain.repository.lookupRepository

import androidx.lifecycle.MutableLiveData
import app.mediabrainz.api.ApiRequestProvider
import app.mediabrainz.api.lookuprequest.WorkLookupIncType.USER_RATINGS
import app.mediabrainz.api.lookuprequest.WorkLookupIncType.USER_TAGS
import app.mediabrainz.domain.OAuthManager
import app.mediabrainz.domain.mapper.WorkMapper
import app.mediabrainz.domain.model.Work
import app.mediabrainz.domain.repository.Resource


class WorkLookupRepository : BaseLookupRepository<Work>() {

    override fun lookup(mutableLiveData: MutableLiveData<Resource<Work>>, mbid: String) {
        if (mbid.isNotBlank()) {
            call(
                mutableLiveData,
                { ApiRequestProvider.createWorkLookupRequest(mbid).lookup() },
                { WorkMapper().mapTo(this) },
                false
            )
        }
    }

    override fun authLookup(mutableLiveData: MutableLiveData<Resource<Work>>, mbid: String) {
        if (mbid.isNotBlank()) {
            call(
                mutableLiveData,
                {
                    ApiRequestProvider.createWorkLookupRequest(mbid)
                        .addIncs(USER_TAGS, USER_RATINGS)
                        .addAccessToken(OAuthManager.accessToken?.token ?: "")
                        .lookup()
                },
                { WorkMapper().mapTo(this) },
                true
            )
        }
    }

}