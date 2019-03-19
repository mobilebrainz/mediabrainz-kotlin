package app.mediabrainz.domain.repository

import androidx.lifecycle.MutableLiveData
import app.mediabrainz.api.ApiRequestProvider
import app.mediabrainz.api.response.ReleaseGroupResponse
import app.mediabrainz.api.searchrequest.ReleaseGroupSearchField.ARTIST
import app.mediabrainz.api.searchrequest.ReleaseGroupSearchField.RELEASE_GROUP
import app.mediabrainz.domain.mapper.PageMapper
import app.mediabrainz.domain.mapper.ReleaseGroupMapper
import app.mediabrainz.domain.model.ReleaseGroup
import app.mediabrainz.domain.parenthesesString


class ReleaseGroupSearchRepository : BaseApiRepository() {

    fun search(mutableLiveData: MutableLiveData<Resource<List<ReleaseGroup>>>, rg: String) {
        search(mutableLiveData, "", rg)
    }

    fun search(
        mutableLiveData: MutableLiveData<Resource<List<ReleaseGroup>>>,
        artist: String, rg: String
    ) {
        val limit = 100
        call(mutableLiveData,
            {
                ApiRequestProvider.createReleaseGroupSearchRequest()
                    .add(ARTIST, parenthesesString(artist))
                    .add(RELEASE_GROUP, parenthesesString(rg))
                    .search(limit, 0)
            },
            {
                PageMapper<ReleaseGroupResponse, ReleaseGroup> { ReleaseGroupMapper().mapTo(it) }.mapToList(getItems())
            }
        )
    }

}
