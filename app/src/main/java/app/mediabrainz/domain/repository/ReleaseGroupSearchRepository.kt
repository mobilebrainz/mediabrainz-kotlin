package app.mediabrainz.domain.repository

import androidx.core.view.OneShotPreDrawListener.add
import app.mediabrainz.api.response.ArtistSearchResponse
import app.mediabrainz.api.ApiRequestProvider
import app.mediabrainz.api.response.ReleaseGroupSearchResponse
import app.mediabrainz.api.searchrequest.ReleaseGroupSearchField
import app.mediabrainz.domain.mapper.ArtistMapper
import app.mediabrainz.domain.mapper.ReleaseGroupMapper
import app.mediabrainz.domain.model.Artist
import app.mediabrainz.domain.model.ReleaseGroup
import app.mediabrainz.domain.parenthesesString


class ReleaseGroupSearchRepository : BaseApiRepository<ReleaseGroupSearchResponse, List<ReleaseGroup>>() {

    fun search(rg: String, limit: Int, offset: Int) {
        mutableLiveData.value = Resource.loading()
        recursiveSearch("", rg, limit,  offset)
    }

    fun search(artist: String, rg: String, limit: Int, offset: Int) {
        mutableLiveData.value = Resource.loading()
        recursiveSearch(artist, rg, limit,  offset)
    }

    private fun recursiveSearch(artist: String, rg: String, limit: Int, offset: Int) {
        val request = ApiRequestProvider.createReleaseGroupSearchRequest()
            .add(ReleaseGroupSearchField.ARTIST, parenthesesString(artist))
            .add(ReleaseGroupSearchField.RELEASE_GROUP, parenthesesString(rg))
            .search(artist, limit, offset)
        call(request,
            { recursiveSearch(artist, rg, limit, offset) },
            { ReleaseGroupMapper().mapToList(releaseGroups) })
    }

}