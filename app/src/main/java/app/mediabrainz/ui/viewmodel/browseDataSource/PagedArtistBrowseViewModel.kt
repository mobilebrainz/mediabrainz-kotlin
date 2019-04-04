package app.mediabrainz.ui.viewmodel.browseDataSource

import app.mediabrainz.api.browserequest.ArtistBrowseEntityType
import app.mediabrainz.domain.datasource.browseDataSource.ArtistBrowseDataSource
import app.mediabrainz.domain.datasource.searchDataSource.ArtistSearchDataSource
import app.mediabrainz.domain.model.Artist
import app.mediabrainz.ui.viewmodel.BaseDataSourceViewModel


class PagedArtistBrowseViewModel : BaseDataSourceViewModel<Artist>() {

    private val initialLoadSize = 25
    private val loadSize = 25

    fun browse(entityType: ArtistBrowseEntityType, mbid: String, authorized: Boolean = false) {
        val factory = ArtistBrowseDataSource.Factory(entityType, mbid, authorized)
        val config = buildPagedListConfig(loadSize, initialLoadSize)
        initPagedItems(config, factory)
    }

}