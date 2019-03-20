package app.mediabrainz.domain.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import app.mediabrainz.api.ApiRequestProvider
import app.mediabrainz.api.response.InstrumentResponse
import app.mediabrainz.api.response.InstrumentSearchResponse
import app.mediabrainz.domain.mapper.InstrumentMapper
import app.mediabrainz.domain.model.Instrument
import app.mediabrainz.domain.parenthesesString


class InstrumentSearchDataSource(val query: String) :
    BaseSearchDataSource<InstrumentResponse, Instrument, InstrumentSearchResponse>() {

    override fun request(loadSize: Int, offset: Int) = ApiRequestProvider.createInstrumentSearchRequest()
        .search(parenthesesString(query), loadSize, offset)

    override fun map() = InstrumentMapper()::mapTo

    class Factory(val query: String) : DataSource.Factory<Int, Instrument>() {
        val dataSourceLiveData = MutableLiveData<DataSourceInterface>()

        override fun create(): PageKeyedDataSource<Int, Instrument> {
            val dataSource = InstrumentSearchDataSource(query)
            dataSourceLiveData.postValue(dataSource)
            return dataSource
        }
    }

}