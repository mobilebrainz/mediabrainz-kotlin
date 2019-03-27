package app.mediabrainz.ui.viewmodel.browseDataSource

import app.mediabrainz.api.browserequest.LabelBrowseEntityType
import app.mediabrainz.domain.datasource.browseDataSource.LabelBrowseDataSource
import app.mediabrainz.domain.model.Label
import app.mediabrainz.ui.viewmodel.BaseDataSourceViewModel


class PagedLabelBrowseViewModel : BaseDataSourceViewModel<Label>() {

    private val initialLoadSize = 25
    private val loadSize = 25

    fun browse(entityType: LabelBrowseEntityType, mbid: String) {
        val factory = LabelBrowseDataSource.Factory(entityType, mbid)
        val config = buildPagedListConfig(loadSize, initialLoadSize)
        initPagedItems(config, factory)
    }

}