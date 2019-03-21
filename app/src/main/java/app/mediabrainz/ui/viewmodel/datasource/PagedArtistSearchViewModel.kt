package app.mediabrainz.ui.viewmodel.datasource

import app.mediabrainz.domain.datasource.ArtistSearchDataSource
import app.mediabrainz.domain.model.Artist


class PagedArtistSearchViewModel : BaseDataSourceViewModel<Artist>() {

    private val initialLoadSize = 25
    private val loadSize = 25

    fun search(query: String) {
        val factory = ArtistSearchDataSource.Factory(query)
        val config = buildPagedListConfig(loadSize, initialLoadSize)
        initPagedItems(config, factory)
    }

}