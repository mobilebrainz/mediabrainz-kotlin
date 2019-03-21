package app.mediabrainz.domain.datasource

import androidx.paging.PageKeyedDataSource
import app.mediabrainz.api.ApiRequestProvider
import app.mediabrainz.api.response.ReleaseResponse
import app.mediabrainz.api.response.ReleaseSearchResponse
import app.mediabrainz.api.searchrequest.ReleaseSearchField.ARTIST
import app.mediabrainz.api.searchrequest.ReleaseSearchField.RELEASE
import app.mediabrainz.domain.datasource.core.DataSourceFactory
import app.mediabrainz.domain.mapper.ReleaseMapper
import app.mediabrainz.domain.model.Release
import app.mediabrainz.domain.parenthesesString


class ReleaseSearchDataSource(val artist: String, val release: String) :
    BaseSearchDataSource<ReleaseResponse, Release, ReleaseSearchResponse>() {

    override fun request(loadSize: Int, offset: Int) = ApiRequestProvider.createReleaseSearchRequest()
        .add(ARTIST, parenthesesString(artist))
        .add(RELEASE, parenthesesString(release))
        .search(loadSize, offset)

    override fun map() = ReleaseMapper()::mapTo

    class Factory(val artist: String, val release: String) : DataSourceFactory<Release>() {
        override fun create(): PageKeyedDataSource<Int, Release> {
            val dataSource = ReleaseSearchDataSource(artist, release)
            setDataSource(dataSource)
            return dataSource
        }
    }

}