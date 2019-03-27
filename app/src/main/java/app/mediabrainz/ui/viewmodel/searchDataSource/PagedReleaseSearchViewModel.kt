package app.mediabrainz.ui.viewmodel.searchDataSource

import app.mediabrainz.domain.datasource.searchDataSource.ReleaseSearchDataSource
import app.mediabrainz.domain.model.Release
import app.mediabrainz.ui.viewmodel.BaseDataSourceViewModel


class PagedReleaseSearchViewModel : BaseDataSourceViewModel<Release>() {

    private val initialLoadSize = 25
    private val loadSize = 25

    fun search(release: String) {
        search("", release)
    }

    fun search(artist: String, release: String) {
        val factory = ReleaseSearchDataSource.Factory(artist, release)
        val config = buildPagedListConfig(loadSize, initialLoadSize)
        initPagedItems(config, factory)
    }

}