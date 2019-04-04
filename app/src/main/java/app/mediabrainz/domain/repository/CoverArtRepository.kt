package app.mediabrainz.domain.repository

import androidx.lifecycle.MutableLiveData
import app.mediabrainz.api.ApiRequestProvider
import app.mediabrainz.domain.mapper.CoverArtMapper
import app.mediabrainz.domain.model.CoverArt


class CoverArtRepository : BaseApiRepository() {

    fun getReleaseCoverArt(mutableLiveData: MutableLiveData<Resource<List<CoverArt>>>, mbid: String) {
        if (mbid.isNotBlank()) {
            call(mutableLiveData,
                { ApiRequestProvider.createCoverArtRequest(mbid).getReleaseCoverArt() },
                { CoverArtMapper().mapToList(this) }
            )
        }
    }

    fun getReleaseGroupCoverArt(mutableLiveData: MutableLiveData<Resource<List<CoverArt>>>, mbid: String) {
        if (mbid.isNotBlank()) {
            call(mutableLiveData,
                { ApiRequestProvider.createCoverArtRequest(mbid).getReleaseGroupCoverArt() },
                { CoverArtMapper().mapToList(this) }
            )
        }
    }

}