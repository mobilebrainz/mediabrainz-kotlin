package app.mediabrainz.ui.viewmodel.browseDataSource

import app.mediabrainz.api.browserequest.WorkBrowseEntityType
import app.mediabrainz.domain.datasource.browseDataSource.WorkBrowseDataSource
import app.mediabrainz.domain.model.Work
import app.mediabrainz.ui.viewmodel.BaseDataSourceViewModel


class PagedWorkBrowseViewModel : BaseDataSourceViewModel<Work>() {

    private val initialLoadSize = 25
    private val loadSize = 25

    fun browse(entityType: WorkBrowseEntityType, mbid: String, authorized: Boolean = false) {
        val factory = WorkBrowseDataSource.Factory(entityType, mbid, authorized)
        val config = buildPagedListConfig(loadSize, initialLoadSize)
        initPagedItems(config, factory)
    }

}