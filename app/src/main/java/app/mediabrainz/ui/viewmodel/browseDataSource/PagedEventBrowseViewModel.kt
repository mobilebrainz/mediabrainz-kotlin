package app.mediabrainz.ui.viewmodel.browseDataSource

import app.mediabrainz.api.browserequest.AreaBrowseEntityType
import app.mediabrainz.api.browserequest.EventBrowseEntityType
import app.mediabrainz.domain.datasource.browseDataSource.AreaBrowseDataSource
import app.mediabrainz.domain.datasource.browseDataSource.EventBrowseDataSource
import app.mediabrainz.domain.model.Area
import app.mediabrainz.domain.model.Event
import app.mediabrainz.ui.viewmodel.BaseDataSourceViewModel


class PagedEventBrowseViewModel : BaseDataSourceViewModel<Event>() {

    private val initialLoadSize = 25
    private val loadSize = 25

    fun browse(entityType: EventBrowseEntityType, mbid: String, authorized: Boolean = false) {
        val factory = EventBrowseDataSource.Factory(entityType, mbid, authorized)
        val config = buildPagedListConfig(loadSize, initialLoadSize)
        initPagedItems(config, factory)
    }

}