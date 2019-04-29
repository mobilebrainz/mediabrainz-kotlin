package app.mediabrainz.domain.datasource.browseDataSource

import androidx.paging.PageKeyedDataSource
import app.mediabrainz.api.ApiRequestProvider
import app.mediabrainz.api.browserequest.ReleaseGroupBrowseEntityType
import app.mediabrainz.api.browserequest.ReleaseGroupBrowseIncType.RATINGS
import app.mediabrainz.api.browserequest.ReleaseGroupBrowseIncType.USER_RATINGS
import app.mediabrainz.api.response.ReleaseGroupBrowseResponse
import app.mediabrainz.api.response.ReleaseGroupResponse
import app.mediabrainz.domain.OAuthManager
import app.mediabrainz.domain.datasource.core.BaseDataSource
import app.mediabrainz.domain.datasource.core.DataSourceFactory
import app.mediabrainz.domain.mapper.ReleaseGroupMapper
import app.mediabrainz.domain.model.RGType
import app.mediabrainz.domain.model.ReleaseGroup
import java.util.*


class ReleaseGroupsByArtistAndTypeDataSource(
    val artistMbid: String,
    val rgType: RGType,
    val releaseGroups: List<ReleaseGroup>?,
    val authorized: Boolean
) : BaseDataSource<ReleaseGroupResponse, ReleaseGroup, ReleaseGroupBrowseResponse>() {

    override fun request(loadSize: Int, offset: Int) =
        if (authorized)
            ApiRequestProvider.createReleaseGroupBrowseRequest(ReleaseGroupBrowseEntityType.ARTIST, artistMbid)
                .addTypes(rgType.type)
                .addAccessToken(OAuthManager.accessToken?.token ?: "")
                .addIncs(RATINGS, USER_RATINGS)
                .browse(loadSize, offset)
        else
            ApiRequestProvider.createReleaseGroupBrowseRequest(ReleaseGroupBrowseEntityType.ARTIST, artistMbid)
                .addTypes(rgType.type)
                .addIncs(RATINGS)
                .addIncs()
                .browse(loadSize, offset)

    override fun map() = ReleaseGroupMapper()::mapTo

    override fun isAuthorized() = authorized

    override fun filter(items: List<ReleaseGroup>): List<ReleaseGroup> {
        Collections.sort(items) { rg1, rg2 -> rg1.getYear() - rg2.getYear() }
        return if (releaseGroups != null) {
            val rgs = ArrayList<ReleaseGroup>()
            for (item in items) {
                if (releaseGroups.contains(item)) {
                    rgs.add(item)
                }
            }
            rgs
        } else items
    }

    class Factory(
        val mbid: String,
        val rgType: RGType,
        val releaseGroups: List<ReleaseGroup>?,
        val authorized: Boolean = false
    ) :
        DataSourceFactory<ReleaseGroup>() {

        override fun create(): PageKeyedDataSource<Int, ReleaseGroup> {
            val dataSource = ReleaseGroupsByArtistAndTypeDataSource(
                mbid,
                rgType,
                releaseGroups,
                authorized
            )
            setDataSource(dataSource)
            return dataSource
        }
    }

}