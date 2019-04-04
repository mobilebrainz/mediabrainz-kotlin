package app.mediabrainz.ui.viewmodel.browseDataSource

import app.mediabrainz.api.browserequest.ReleaseGroupBrowseEntityType
import app.mediabrainz.domain.datasource.browseDataSource.ReleaseGroupBrowseDataSource
import app.mediabrainz.domain.model.ReleaseGroup
import app.mediabrainz.ui.viewmodel.BaseDataSourceViewModel


class PagedReleaseGroupBrowseViewModel : BaseDataSourceViewModel<ReleaseGroup>() {

    private val initialLoadSize = 25
    private val loadSize = 25

    fun browse(entityType: ReleaseGroupBrowseEntityType, mbid: String, authorized: Boolean = false) {
        val factory = ReleaseGroupBrowseDataSource.Factory(entityType, mbid, authorized)
        val config = buildPagedListConfig(loadSize, initialLoadSize)
        initPagedItems(config, factory)
    }

}