package app.mediabrainz.domain.datasource.searchDataSource

import androidx.paging.PageKeyedDataSource
import app.mediabrainz.api.ApiRequestProvider
import app.mediabrainz.api.response.ReleaseGroupResponse
import app.mediabrainz.api.response.ReleaseGroupSearchResponse
import app.mediabrainz.api.searchrequest.ReleaseGroupSearchField.ARTIST
import app.mediabrainz.api.searchrequest.ReleaseGroupSearchField.RELEASE_GROUP
import app.mediabrainz.domain.datasource.core.BaseDataSource
import app.mediabrainz.domain.datasource.core.DataSourceFactory
import app.mediabrainz.domain.mapper.ReleaseGroupMapper
import app.mediabrainz.domain.model.ReleaseGroup
import app.mediabrainz.domain.parenthesesString


class ReleaseGroupSearchDataSource(val artist: String, val rg: String) :
    BaseDataSource<ReleaseGroupResponse, ReleaseGroup, ReleaseGroupSearchResponse>() {

    override fun request(loadSize: Int, offset: Int) = ApiRequestProvider.createReleaseGroupSearchRequest()
        .add(ARTIST, parenthesesString(artist))
        .add(RELEASE_GROUP, parenthesesString(rg))
        .search(loadSize, offset)

    override fun map() = ReleaseGroupMapper()::mapTo

    class Factory(val artist: String, val rg: String) : DataSourceFactory<ReleaseGroup>() {
        override fun create(): PageKeyedDataSource<Int, ReleaseGroup> {
            val dataSource = ReleaseGroupSearchDataSource(artist, rg)
            setDataSource(dataSource)
            return dataSource
        }
    }

}