package app.mediabrainz.domain.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import app.mediabrainz.api.ApiRequestProvider
import app.mediabrainz.api.response.ReleaseGroupResponse
import app.mediabrainz.api.response.ReleaseGroupSearchResponse
import app.mediabrainz.api.searchrequest.ReleaseGroupSearchField
import app.mediabrainz.api.searchrequest.ReleaseGroupSearchField.*
import app.mediabrainz.domain.mapper.ReleaseGroupMapper
import app.mediabrainz.domain.model.ReleaseGroup
import app.mediabrainz.domain.parenthesesString


class ReleaseGroupSearchDataSource(val artist: String, val rg: String) :
    BaseSearchDataSource<ReleaseGroupResponse, ReleaseGroup, ReleaseGroupSearchResponse>() {

    override fun request(loadSize: Int, offset: Int) = ApiRequestProvider.createReleaseGroupSearchRequest()
        .add(ARTIST, parenthesesString(artist))
        .add(RELEASE_GROUP, parenthesesString(rg))
        .search(loadSize, offset)

    override fun map() = ReleaseGroupMapper()::mapTo

    class Factory(val artist: String, val rg: String) : DataSource.Factory<Int, ReleaseGroup>() {
        val dataSourceLiveData = MutableLiveData<DataSourceInterface>()

        override fun create(): PageKeyedDataSource<Int, ReleaseGroup> {
            val dataSource = ReleaseGroupSearchDataSource(artist, rg)
            dataSourceLiveData.postValue(dataSource)
            return dataSource
        }
    }

}