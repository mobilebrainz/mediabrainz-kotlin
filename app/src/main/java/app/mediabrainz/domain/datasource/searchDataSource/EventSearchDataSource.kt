package app.mediabrainz.domain.datasource.searchDataSource

import androidx.paging.PageKeyedDataSource
import app.mediabrainz.api.ApiRequestProvider
import app.mediabrainz.api.response.EventResponse
import app.mediabrainz.api.response.EventSearchResponse
import app.mediabrainz.domain.datasource.core.BaseDataSource
import app.mediabrainz.domain.datasource.core.DataSourceFactory
import app.mediabrainz.domain.mapper.EventMapper
import app.mediabrainz.domain.model.Event
import app.mediabrainz.domain.parenthesesString


class EventSearchDataSource(val query: String) :
    BaseDataSource<EventResponse, Event, EventSearchResponse>() {

    override fun request(loadSize: Int, offset: Int) = ApiRequestProvider.createEventSearchRequest()
        .search(parenthesesString(query), loadSize, offset)

    override fun map() = EventMapper()::mapTo

    class Factory(val query: String) : DataSourceFactory<Event>() {
        override fun create(): PageKeyedDataSource<Int, Event> {
            val dataSource = EventSearchDataSource(query)
            setDataSource(dataSource)
            return dataSource
        }
    }

}