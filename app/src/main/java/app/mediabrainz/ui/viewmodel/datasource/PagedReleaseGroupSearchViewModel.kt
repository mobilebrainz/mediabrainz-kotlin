package app.mediabrainz.ui.viewmodel.datasource

import app.mediabrainz.domain.datasource.ReleaseGroupSearchDataSource
import app.mediabrainz.domain.model.ReleaseGroup


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