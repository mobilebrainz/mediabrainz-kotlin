package app.mediabrainz.domain.datasource.browseDataSource

import androidx.paging.PageKeyedDataSource
import app.mediabrainz.api.ApiRequestProvider
import app.mediabrainz.api.browserequest.EventBrowseEntityType
import app.mediabrainz.api.browserequest.EventBrowseIncType.USER_RATINGS
import app.mediabrainz.api.browserequest.EventBrowseIncType.USER_TAGS
import app.mediabrainz.api.response.EventBrowseResponse
import app.mediabrainz.api.response.EventResponse
import app.mediabrainz.domain.OAuthManager
import app.mediabrainz.domain.datasource.core.BaseDataSource
import app.mediabrainz.domain.datasource.core.DataSourceFactory
import app.mediabrainz.domain.mapper.EventMapper
import app.mediabrainz.domain.model.Event


class EventBrowseDataSource(val entityType: EventBrowseEntityType, val mbid: String, val authorized: Boolean) :
    BaseDataSource<EventResponse, Event, EventBrowseResponse>() {

    override fun request(loadSize: Int, offset: Int) =
        if (authorized)
            ApiRequestProvider.createEventBrowseRequest(entityType, mbid)
                .addIncs(USER_TAGS, USER_RATINGS)
                .addAccessToken(OAuthManager.accessToken?.token ?: "")
                .browse(loadSize, offset)
        else
            ApiRequestProvider.createEventBrowseRequest(entityType, mbid)
                .browse(loadSize, offset)


    override fun map() = EventMapper()::mapTo

    override fun isAuthorized() = authorized

    class Factory(val entityType: EventBrowseEntityType, val mbid: String, val authorized: Boolean = false) :
        DataSourceFactory<Event>() {

        override fun create(): PageKeyedDataSource<Int, Event> {
            val dataSource = EventBrowseDataSource(entityType, mbid, authorized)
            setDataSource(dataSource)
            return dataSource
        }
    }
}