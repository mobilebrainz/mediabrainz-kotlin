package app.mediabrainz.domain.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import app.mediabrainz.api.ApiRequestProvider
import app.mediabrainz.api.response.SeriesResponse
import app.mediabrainz.api.response.SeriesSearchResponse
import app.mediabrainz.domain.mapper.SeriesMapper
import app.mediabrainz.domain.model.Series
import app.mediabrainz.domain.parenthesesString


class SeriesSearchDataSource(val query: String) :
    BaseSearchDataSource<SeriesResponse, Series, SeriesSearchResponse>() {

    override fun request(loadSize: Int, offset: Int) = ApiRequestProvider.createSeriesSearchRequest()
        .search(parenthesesString(query), loadSize, offset)

    override fun map() = SeriesMapper()::mapTo

    class Factory(val query: String) : DataSource.Factory<Int, Series>() {
        val dataSourceLiveData = MutableLiveData<DataSourceInterface>()

        override fun create(): PageKeyedDataSource<Int, Series> {
            val dataSource = SeriesSearchDataSource(query)
            dataSourceLiveData.postValue(dataSource)
            return dataSource
        }
    }

}