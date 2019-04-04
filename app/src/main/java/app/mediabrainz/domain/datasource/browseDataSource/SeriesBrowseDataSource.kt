package app.mediabrainz.domain.datasource.browseDataSource

import androidx.paging.PageKeyedDataSource
import app.mediabrainz.api.ApiRequestProvider
import app.mediabrainz.api.browserequest.SeriesBrowseEntityType
import app.mediabrainz.api.browserequest.SeriesBrowseIncType
import app.mediabrainz.api.response.SeriesBrowseResponse
import app.mediabrainz.api.response.SeriesResponse
import app.mediabrainz.domain.OAuthManager
import app.mediabrainz.domain.datasource.core.BaseDataSource
import app.mediabrainz.domain.datasource.core.DataSourceFactory
import app.mediabrainz.domain.mapper.SeriesMapper
import app.mediabrainz.domain.model.Series


class SeriesBrowseDataSource(val entityType: SeriesBrowseEntityType, val mbid: String, val authorized: Boolean) :
    BaseDataSource<SeriesResponse, Series, SeriesBrowseResponse>() {

    override fun request(loadSize: Int, offset: Int) =
        if (authorized)
            ApiRequestProvider.createSeriesBrowseRequest(entityType, mbid)
                .addIncs(SeriesBrowseIncType.USER_TAGS)
                .addAccessToken(OAuthManager.accessToken?.token ?: "")
                .browse(loadSize, offset)
        else
            ApiRequestProvider.createSeriesBrowseRequest(entityType, mbid)
                .browse(loadSize, offset)

    override fun map() = SeriesMapper()::mapTo

    override fun isAuthorized() = authorized

    class Factory(val entityType: SeriesBrowseEntityType, val mbid: String, val authorized: Boolean = false) :
        DataSourceFactory<Series>() {

        override fun create(): PageKeyedDataSource<Int, Series> {
            val dataSource = SeriesBrowseDataSource(entityType, mbid, authorized)
            setDataSource(dataSource)
            return dataSource
        }
    }
}