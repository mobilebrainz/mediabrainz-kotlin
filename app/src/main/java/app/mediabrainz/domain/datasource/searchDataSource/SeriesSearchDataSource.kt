package app.mediabrainz.domain.datasource.searchDataSource

import androidx.paging.PageKeyedDataSource
import app.mediabrainz.api.ApiRequestProvider
import app.mediabrainz.api.response.SeriesResponse
import app.mediabrainz.api.response.SeriesSearchResponse
import app.mediabrainz.domain.datasource.core.BaseDataSource
import app.mediabrainz.domain.datasource.core.DataSourceFactory
import app.mediabrainz.domain.mapper.SeriesMapper
import app.mediabrainz.domain.model.Series
import app.mediabrainz.domain.parenthesesString


class SeriesSearchDataSource(val query: String) :
    BaseDataSource<SeriesResponse, Series, SeriesSearchResponse>() {

    override fun request(loadSize: Int, offset: Int) = ApiRequestProvider.createSeriesSearchRequest()
        .search(parenthesesString(query), loadSize, offset)

    override fun map() = SeriesMapper()::mapTo

    class Factory(val query: String) : DataSourceFactory<Series>() {
        override fun create(): PageKeyedDataSource<Int, Series> {
            val dataSource = SeriesSearchDataSource(query)
            setDataSource(dataSource)
            return dataSource
        }
    }

}