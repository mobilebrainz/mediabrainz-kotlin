package app.mediabrainz.domain.datasource.searchDataSource

import androidx.paging.PageKeyedDataSource
import app.mediabrainz.api.ApiRequestProvider
import app.mediabrainz.api.response.InstrumentResponse
import app.mediabrainz.api.response.InstrumentSearchResponse
import app.mediabrainz.domain.datasource.core.BaseDataSource
import app.mediabrainz.domain.datasource.core.DataSourceFactory
import app.mediabrainz.domain.mapper.InstrumentMapper
import app.mediabrainz.domain.model.Instrument
import app.mediabrainz.domain.parenthesesString


class InstrumentSearchDataSource(val query: String) :
    BaseDataSource<InstrumentResponse, Instrument, InstrumentSearchResponse>() {

    override fun request(loadSize: Int, offset: Int) = ApiRequestProvider.createInstrumentSearchRequest()
        .search(parenthesesString(query), loadSize, offset)

    override fun map() = InstrumentMapper()::mapTo

    class Factory(val query: String) : DataSourceFactory<Instrument>() {
        override fun create(): PageKeyedDataSource<Int, Instrument> {
            val dataSource = InstrumentSearchDataSource(query)
            setDataSource(dataSource)
            return dataSource
        }
    }

}