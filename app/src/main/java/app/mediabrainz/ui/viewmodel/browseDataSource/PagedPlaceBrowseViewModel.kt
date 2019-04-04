package app.mediabrainz.ui.viewmodel.browseDataSource

import app.mediabrainz.api.browserequest.PlaceBrowseEntityType
import app.mediabrainz.domain.datasource.browseDataSource.PlaceBrowseDataSource
import app.mediabrainz.domain.model.Place
import app.mediabrainz.ui.viewmodel.BaseDataSourceViewModel


class PagedPlaceBrowseViewModel : BaseDataSourceViewModel<Place>() {

    private val initialLoadSize = 25
    private val loadSize = 25

    fun browse(entityType: PlaceBrowseEntityType, mbid: String, authorized: Boolean = false) {
        val factory = PlaceBrowseDataSource.Factory(entityType, mbid, authorized)
        val config = buildPagedListConfig(loadSize, initialLoadSize)
        initPagedItems(config, factory)
    }

}