package app.mediabrainz.ui.viewmodel.browseDataSource

import app.mediabrainz.domain.datasource.browseDataSource.ReleaseGroupsByArtistAndTypeDataSource
import app.mediabrainz.domain.model.RGType
import app.mediabrainz.domain.model.ReleaseGroup
import app.mediabrainz.ui.viewmodel.BaseDataSourceViewModel


class PagedReleaseGroupsByArtistAndTypeViewModel : BaseDataSourceViewModel<ReleaseGroup>() {

    private val initialLoadSize = 25
    private val loadSize = 25

    fun browse(artistMbid: String, rgType: RGType, authorized: Boolean = false) {
        val factory = ReleaseGroupsByArtistAndTypeDataSource.Factory(artistMbid, rgType, authorized)
        val config = buildPagedListConfig(loadSize, initialLoadSize)
        initPagedItems(config, factory)
    }

}