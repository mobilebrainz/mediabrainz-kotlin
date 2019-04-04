package app.mediabrainz.domain.datasource.browseDataSource

import androidx.paging.PageKeyedDataSource
import app.mediabrainz.api.ApiRequestProvider
import app.mediabrainz.api.browserequest.LabelBrowseEntityType
import app.mediabrainz.api.browserequest.LabelBrowseIncType.USER_RATINGS
import app.mediabrainz.api.browserequest.LabelBrowseIncType.USER_TAGS
import app.mediabrainz.api.response.LabelBrowseResponse
import app.mediabrainz.api.response.LabelResponse
import app.mediabrainz.domain.OAuthManager
import app.mediabrainz.domain.datasource.core.BaseDataSource
import app.mediabrainz.domain.datasource.core.DataSourceFactory
import app.mediabrainz.domain.mapper.LabelMapper
import app.mediabrainz.domain.model.Label


class LabelBrowseDataSource(val entityType: LabelBrowseEntityType, val mbid: String, val authorized: Boolean) :
    BaseDataSource<LabelResponse, Label, LabelBrowseResponse>() {

    override fun request(loadSize: Int, offset: Int) =
        if (authorized)
            ApiRequestProvider.createLabelBrowseRequest(entityType, mbid)
                .addIncs(USER_TAGS, USER_RATINGS)
                .addAccessToken(OAuthManager.accessToken?.token ?: "")
                .browse(loadSize, offset)
        else
            ApiRequestProvider.createLabelBrowseRequest(entityType, mbid)
                .browse(loadSize, offset)

    override fun map() = LabelMapper()::mapTo

    override fun isAuthorized() = authorized

    class Factory(val entityType: LabelBrowseEntityType, val mbid: String, val authorized: Boolean = false) :
        DataSourceFactory<Label>() {

        override fun create(): PageKeyedDataSource<Int, Label> {
            val dataSource = LabelBrowseDataSource(entityType, mbid, authorized)
            setDataSource(dataSource)
            return dataSource
        }
    }
}