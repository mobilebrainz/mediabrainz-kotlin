package app.mediabrainz.ui.viewmodel.searchDataSource

import app.mediabrainz.domain.datasource.searchDataSource.ReleaseGroupSearchDataSource
import app.mediabrainz.domain.model.ReleaseGroup
import app.mediabrainz.ui.viewmodel.BaseDataSourceViewModel


class PagedReleaseGroupSearchViewModel : BaseDataSourceViewModel<ReleaseGroup>() {

    private val initialLoadSize = 25
    private val loadSize = 25

    fun search(rg: String) {
        search("", rg)
    }

    fun search(artist: String, rg: String) {
        val factory = ReleaseGroupSearchDataSource.Factory(artist, rg)
        val config = buildPagedListConfig(loadSize, initialLoadSize)
        initPagedItems(config, factory)
    }

}