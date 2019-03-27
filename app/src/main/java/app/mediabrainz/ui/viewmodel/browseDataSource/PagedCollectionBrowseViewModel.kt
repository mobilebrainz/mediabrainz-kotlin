package app.mediabrainz.ui.viewmodel.browseDataSource

import app.mediabrainz.api.browserequest.CollectionBrowseEntityType
import app.mediabrainz.domain.datasource.browseDataSource.CollectionBrowseDataSource
import app.mediabrainz.domain.model.Collection
import app.mediabrainz.ui.viewmodel.BaseDataSourceViewModel


class PagedCollectionBrowseViewModel : BaseDataSourceViewModel<Collection>() {

    private val initialLoadSize = 25
    private val loadSize = 25

    fun browse(entityType: CollectionBrowseEntityType, mbid: String) {
        val factory = CollectionBrowseDataSource.Factory(entityType, mbid)
        val config = buildPagedListConfig(loadSize, initialLoadSize)
        initPagedItems(config, factory)
    }

}