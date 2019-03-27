package app.mediabrainz.ui.viewmodel.searchDataSource

import app.mediabrainz.domain.datasource.searchDataSource.InstrumentSearchDataSource
import app.mediabrainz.domain.model.Instrument
import app.mediabrainz.ui.viewmodel.BaseDataSourceViewModel


class PagedInstrumentSearchViewModel : BaseDataSourceViewModel<Instrument>() {

    private val initialLoadSize = 25
    private val loadSize = 25

    fun search(query: String) {
        val factory = InstrumentSearchDataSource.Factory(query)
        val config = buildPagedListConfig(loadSize, initialLoadSize)
        initPagedItems(config, factory)
    }

}