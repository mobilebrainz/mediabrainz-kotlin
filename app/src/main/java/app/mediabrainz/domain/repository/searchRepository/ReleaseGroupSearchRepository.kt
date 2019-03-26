package app.mediabrainz.domain.repository.searchRepository

import androidx.lifecycle.MutableLiveData
import app.mediabrainz.api.ApiRequestProvider
import app.mediabrainz.api.response.ReleaseGroupResponse
import app.mediabrainz.api.searchrequest.ReleaseGroupSearchField.ARTIST
import app.mediabrainz.api.searchrequest.ReleaseGroupSearchField.RELEASE_GROUP
import app.mediabrainz.domain.mapper.PageMapper
import app.mediabrainz.domain.mapper.ReleaseGroupMapper
import app.mediabrainz.domain.model.ReleaseGroup
import app.mediabrainz.domain.parenthesesString
import app.mediabrainz.domain.repository.BaseApiRepository
import app.mediabrainz.domain.repository.Resource


class ReleaseGroupSearchRepository : BaseSearchRepository<ReleaseGroup>() {

    override fun search(mutableLiveData: MutableLiveData<Resource<List<ReleaseGroup>>>, query: String) {
        search(mutableLiveData, "", query)
    }

    fun search(
        mutableLiveData: MutableLiveData<Resource<List<ReleaseGroup>>>,
        artist: String, query: String
    ) {
        if (query.isNotBlank()) {
            val limit = 100
            call(mutableLiveData,
                {
                    ApiRequestProvider.createReleaseGroupSearchRequest()
                        .add(ARTIST, parenthesesString(artist))
                        .add(RELEASE_GROUP, parenthesesString(query))
                        .search(limit, 0)
                },
                {
                    PageMapper<ReleaseGroupResponse, ReleaseGroup> { ReleaseGroupMapper().mapTo(it) }.mapToList(getItems())
                }
            )
        }
    }

}
