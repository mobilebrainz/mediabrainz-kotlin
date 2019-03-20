package app.mediabrainz.domain.repository

import androidx.lifecycle.MutableLiveData
import app.mediabrainz.api.ApiRequestProvider
import app.mediabrainz.api.response.ReleaseResponse
import app.mediabrainz.api.searchrequest.ReleaseSearchField.ARTIST
import app.mediabrainz.api.searchrequest.ReleaseSearchField.RELEASE
import app.mediabrainz.domain.mapper.PageMapper
import app.mediabrainz.domain.mapper.ReleaseMapper
import app.mediabrainz.domain.model.Release
import app.mediabrainz.domain.parenthesesString


class ReleaseSearchRepository : BaseApiRepository() {

    fun search(mutableLiveData: MutableLiveData<Resource<List<Release>>>, release: String) {
        search(mutableLiveData, "", release)
    }

    fun search(
        mutableLiveData: MutableLiveData<Resource<List<Release>>>,
        artist: String, release: String
    ) {
        val limit = 100
        call(mutableLiveData,
            {
                ApiRequestProvider.createReleaseSearchRequest()
                    .add(ARTIST, parenthesesString(artist))
                    .add(RELEASE, parenthesesString(release))
                    .search(limit, 0)
            },
            {
                PageMapper<ReleaseResponse, Release> { ReleaseMapper().mapTo(it) }.mapToList(getItems())
            }
        )
    }

}
