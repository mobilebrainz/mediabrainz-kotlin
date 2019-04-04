package app.mediabrainz.ui.viewmodel.browseDataSource

import app.mediabrainz.api.browserequest.RecordingBrowseEntityType
import app.mediabrainz.domain.datasource.browseDataSource.RecordingBrowseDataSource
import app.mediabrainz.domain.model.Recording
import app.mediabrainz.ui.viewmodel.BaseDataSourceViewModel


class PagedRecordingBrowseViewModel : BaseDataSourceViewModel<Recording>() {

    private val initialLoadSize = 25
    private val loadSize = 25

    fun browse(entityType: RecordingBrowseEntityType, mbid: String, authorized: Boolean = false) {
        val factory = RecordingBrowseDataSource.Factory(entityType, mbid, authorized)
        val config = buildPagedListConfig(loadSize, initialLoadSize)
        initPagedItems(config, factory)
    }

}