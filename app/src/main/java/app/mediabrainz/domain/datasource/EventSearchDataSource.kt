package app.mediabrainz.domain.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import app.mediabrainz.api.ApiRequestProvider
import app.mediabrainz.api.response.EventResponse
import app.mediabrainz.api.response.EventSearchResponse
import app.mediabrainz.domain.mapper.EventMapper
import app.mediabrainz.domain.model.Event
import app.mediabrainz.domain.parenthesesString


class EventSearchDataSource(val query: String) :
    BaseSearchDataSource<EventResponse, Event, EventSearchResponse>() {

    override fun request(loadSize: Int, offset: Int) = ApiRequestProvider.createEventSearchRequest()
        .search(parenthesesString(query), loadSize, offset)

    override fun map() = EventMapper()::mapTo

    class Factory(val query: String) : DataSource.Factory<Int, Event>() {
        val dataSourceLiveData = MutableLiveData<DataSourceInterface>()

        override fun create(): PageKeyedDataSource<Int, Event> {
            val dataSource = EventSearchDataSource(query)
            dataSourceLiveData.postValue(dataSource)
            return dataSource
        }
    }

}