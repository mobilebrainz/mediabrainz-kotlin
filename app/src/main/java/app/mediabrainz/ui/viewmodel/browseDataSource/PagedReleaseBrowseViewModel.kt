package app.mediabrainz.ui.viewmodel.browseDataSource

import app.mediabrainz.api.browserequest.ReleaseBrowseEntityType
import app.mediabrainz.domain.datasource.browseDataSource.ReleaseBrowseDataSource
import app.mediabrainz.domain.model.Release
import app.mediabrainz.ui.viewmodel.BaseDataSourceViewModel


class PagedReleaseBrowseViewModel : BaseDataSourceViewModel<Release>() {

    private val initialLoadSize = 25
    private val loadSize = 25

    fun browse(entityType: ReleaseBrowseEntityType, mbid: String) {
        val factory = ReleaseBrowseDataSource.Factory(entityType, mbid)
        val config = buildPagedListConfig(loadSize, initialLoadSize)
        initPagedItems(config, factory)
    }

}