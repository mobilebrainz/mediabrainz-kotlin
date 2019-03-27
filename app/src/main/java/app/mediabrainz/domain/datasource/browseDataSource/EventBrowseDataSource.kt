package app.mediabrainz.domain.datasource.browseDataSource

import androidx.paging.PageKeyedDataSource
import app.mediabrainz.api.ApiRequestProvider
import app.mediabrainz.api.browserequest.EventBrowseEntityType
import app.mediabrainz.api.response.EventBrowseResponse
import app.mediabrainz.api.response.EventResponse
import app.mediabrainz.domain.datasource.core.BaseDataSource
import app.mediabrainz.domain.datasource.core.DataSourceFactory
import app.mediabrainz.domain.mapper.EventMapper
import app.mediabrainz.domain.model.Event


class EventBrowseDataSource(val entityType: EventBrowseEntityType, val mbid: String) :
    BaseDataSource<EventResponse, Event, EventBrowseResponse>() {

    override fun request(loadSize: Int, offset: Int) =
        ApiRequestProvider.createEventBrowseRequest(entityType, mbid)
            .browse(loadSize, offset)

    override fun map() = EventMapper()::mapTo

    class Factory(val entityType: EventBrowseEntityType, val mbid: String) :
        DataSourceFactory<Event>() {

        override fun create(): PageKeyedDataSource<Int, Event> {
            val dataSource = EventBrowseDataSource(entityType, mbid)
            setDataSource(dataSource)
            return dataSource
        }
    }
}