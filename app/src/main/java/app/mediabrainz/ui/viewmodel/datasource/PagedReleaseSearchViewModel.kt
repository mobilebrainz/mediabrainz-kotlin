package app.mediabrainz.ui.viewmodel.datasource

import app.mediabrainz.domain.datasource.ReleaseSearchDataSource
import app.mediabrainz.domain.model.Release


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