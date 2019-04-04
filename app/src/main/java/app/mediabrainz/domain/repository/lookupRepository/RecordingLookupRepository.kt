package app.mediabrainz.domain.repository.lookupRepository

import androidx.lifecycle.MutableLiveData
import app.mediabrainz.api.ApiRequestProvider
import app.mediabrainz.api.lookuprequest.RecordingLookupIncType.*
import app.mediabrainz.domain.OAuthManager
import app.mediabrainz.domain.mapper.RecordingMapper
import app.mediabrainz.domain.model.Recording
import app.mediabrainz.domain.repository.Resource


class RecordingLookupRepository : BaseLookupRepository<Recording>() {

    override fun lookup(mutableLiveData: MutableLiveData<Resource<Recording>>, mbid: String) {
        if (mbid.isNotBlank()) {
            call(mutableLiveData,
                { ApiRequestProvider.createRecordingLookupRequest(mbid).lookup() },
                { RecordingMapper().mapTo(this) })
        }
    }

    override fun authLookup(mutableLiveData: MutableLiveData<Resource<Recording>>, mbid: String) {
        if (mbid.isNotBlank()) {
            call(mutableLiveData,
                {
                    ApiRequestProvider.createRecordingLookupRequest(mbid)
                        .addIncs(USER_TAGS, USER_GENRES, USER_RATINGS)
                        .addAccessToken(OAuthManager.accessToken?.token ?: "")
                        .lookup()
                },
                { RecordingMapper().mapTo(this) })
        }
    }

}