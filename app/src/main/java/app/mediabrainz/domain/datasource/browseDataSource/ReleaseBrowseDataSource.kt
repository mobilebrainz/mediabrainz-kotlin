package app.mediabrainz.domain.datasource.browseDataSource

import androidx.paging.PageKeyedDataSource
import app.mediabrainz.api.ApiRequestProvider
import app.mediabrainz.api.browserequest.ReleaseBrowseEntityType
import app.mediabrainz.api.browserequest.ReleaseBrowseIncType
import app.mediabrainz.api.response.ReleaseBrowseResponse
import app.mediabrainz.api.response.ReleaseResponse
import app.mediabrainz.domain.datasource.core.BaseDataSource
import app.mediabrainz.domain.datasource.core.DataSourceFactory
import app.mediabrainz.domain.mapper.ReleaseMapper
import app.mediabrainz.domain.model.Release


class ReleaseBrowseDataSource(val entityType: ReleaseBrowseEntityType, val mbid: String) :
    BaseDataSource<ReleaseResponse, Release, ReleaseBrowseResponse>() {

    override fun request(loadSize: Int, offset: Int) =
        ApiRequestProvider.createReleaseBrowseRequest(entityType, mbid)
            .addIncs(
                ReleaseBrowseIncType.ARTIST_CREDITS,
                ReleaseBrowseIncType.LABELS,
                ReleaseBrowseIncType.MEDIA,
                ReleaseBrowseIncType.RELEASE_GROUPS
            )
            .browse(loadSize, offset)

    override fun map() = ReleaseMapper()::mapTo

    override fun isAuthorized() = false

    class Factory(val entityType: ReleaseBrowseEntityType, val mbid: String) :
        DataSourceFactory<Release>() {

        override fun create(): PageKeyedDataSource<Int, Release> {
            val dataSource = ReleaseBrowseDataSource(entityType, mbid)
            setDataSource(dataSource)
            return dataSource
        }
    }
}