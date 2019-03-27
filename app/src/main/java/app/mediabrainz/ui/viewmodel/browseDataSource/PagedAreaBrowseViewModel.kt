package app.mediabrainz.ui.viewmodel.browseDataSource

import app.mediabrainz.api.browserequest.AreaBrowseEntityType
import app.mediabrainz.domain.datasource.browseDataSource.AreaBrowseDataSource
import app.mediabrainz.domain.model.Area
import app.mediabrainz.ui.viewmodel.BaseDataSourceViewModel


class PagedAreaBrowseViewModel : BaseDataSourceViewModel<Area>() {

    private val initialLoadSize = 25
    private val loadSize = 25

    fun browse(entityType: AreaBrowseEntityType, mbid: String) {
        val factory = AreaBrowseDataSource.Factory(entityType, mbid)
        val config = buildPagedListConfig(loadSize, initialLoadSize)
        initPagedItems(config, factory)
    }

}