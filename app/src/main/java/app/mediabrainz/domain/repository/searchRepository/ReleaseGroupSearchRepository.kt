package app.mediabrainz.domain.repository.searchRepository

import androidx.lifecycle.MutableLiveData
import app.mediabrainz.api.ApiRequestProvider
import app.mediabrainz.api.response.ReleaseGroupPrimaryType
import app.mediabrainz.api.response.ReleaseGroupResponse
import app.mediabrainz.api.response.ReleaseGroupSecondaryType
import app.mediabrainz.api.response.ReleaseStatusResponse
import app.mediabrainz.api.searchrequest.ReleaseGroupSearchField
import app.mediabrainz.api.searchrequest.ReleaseGroupSearchField.ARTIST
import app.mediabrainz.api.searchrequest.ReleaseGroupSearchField.RELEASE_GROUP
import app.mediabrainz.domain.mapper.PageMapper
import app.mediabrainz.domain.mapper.ReleaseGroupMapper
import app.mediabrainz.domain.model.*
import app.mediabrainz.domain.parenthesesString
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
            call(
                mutableLiveData,
                {
                    ApiRequestProvider.createReleaseGroupSearchRequest()
                        .add(ARTIST, parenthesesString(artist))
                        .add(RELEASE_GROUP, parenthesesString(query))
                        .search(limit, 0)
                },
                {
                    PageMapper<ReleaseGroupResponse, ReleaseGroup> { ReleaseGroupMapper().mapTo(it) }.mapToList(items)
                },
                false
            )
        }
    }

    fun searchOfficialReleaseGroups(
        mutableLiveData: MutableLiveData<Resource<List<ReleaseGroup>>>,
        artistMbid: String,
        type: RGType,
        offset: Int, limit: Int
    ) {
        var primaryType: ReleaseGroupPrimaryType? = null
        var secondaryType: ReleaseGroupSecondaryType? = null

        when (type) {
            RGPrimaryType.ALBUM -> {
                primaryType = ReleaseGroupPrimaryType.ALBUM
                secondaryType = ReleaseGroupSecondaryType.NOTHING
            }
            RGPrimaryType.EP -> {
                primaryType = ReleaseGroupPrimaryType.EP
                secondaryType = ReleaseGroupSecondaryType.NOTHING
            }
            RGPrimaryType.SINGLE -> {
                primaryType = ReleaseGroupPrimaryType.SINGLE
                secondaryType = ReleaseGroupSecondaryType.NOTHING
            }
            RGSecondaryType.LIVE -> {
                primaryType = ReleaseGroupPrimaryType.EMPTY
                secondaryType = ReleaseGroupSecondaryType.LIVE
            }
            RGSecondaryType.COMPILATION -> {
                primaryType = ReleaseGroupPrimaryType.EMPTY
                secondaryType = ReleaseGroupSecondaryType.COMPILATION
            }
        }

        if (primaryType != null && secondaryType != null) {
            call(
                mutableLiveData,
                {
                    ApiRequestProvider.createReleaseGroupSearchRequest()
                        .addPrimaryType(primaryType)
                        .addSecondaryType(secondaryType)
                        .add(ReleaseGroupSearchField.STATUS, ReleaseStatusResponse.OFFICIAL.status)
                        .add(ReleaseGroupSearchField.ARID, artistMbid)
                        .search(limit, offset)
                },
                {
                    PageMapper<ReleaseGroupResponse, ReleaseGroup> { ReleaseGroupMapper().mapTo(it) }.mapToList(items)
                },
                false
            )
        }
    }



}
