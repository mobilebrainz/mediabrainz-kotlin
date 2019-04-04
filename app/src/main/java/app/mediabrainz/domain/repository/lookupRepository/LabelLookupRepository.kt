package app.mediabrainz.domain.repository.lookupRepository

import androidx.lifecycle.MutableLiveData
import app.mediabrainz.api.ApiRequestProvider
import app.mediabrainz.api.lookuprequest.LabelLookupIncType.USER_RATINGS
import app.mediabrainz.api.lookuprequest.LabelLookupIncType.USER_TAGS
import app.mediabrainz.domain.OAuthManager
import app.mediabrainz.domain.mapper.LabelMapper
import app.mediabrainz.domain.model.Label
import app.mediabrainz.domain.repository.Resource


class LabelLookupRepository : BaseLookupRepository<Label>() {

    override fun lookup(mutableLiveData: MutableLiveData<Resource<Label>>, mbid: String) {
        if (mbid.isNotBlank()) {
            call(mutableLiveData,
                { ApiRequestProvider.createLabelLookupRequest(mbid).lookup() },
                { LabelMapper().mapTo(this) })
        }
    }

    override fun authLookup(mutableLiveData: MutableLiveData<Resource<Label>>, mbid: String) {
        if (mbid.isNotBlank()) {
            call(mutableLiveData,
                {
                    ApiRequestProvider.createLabelLookupRequest(mbid)
                        .addIncs(USER_TAGS, USER_RATINGS)
                        .addAccessToken(OAuthManager.accessToken?.token ?: "")
                        .lookup()
                },
                { LabelMapper().mapTo(this) })
        }
    }

}