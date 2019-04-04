package app.mediabrainz.ui.viewmodel.browseDataSource

import app.mediabrainz.api.browserequest.SeriesBrowseEntityType
import app.mediabrainz.domain.datasource.browseDataSource.SeriesBrowseDataSource
import app.mediabrainz.domain.model.Series
import app.mediabrainz.ui.viewmodel.BaseDataSourceViewModel


class PagedSeriesBrowseViewModel : BaseDataSourceViewModel<Series>() {

    private val initialLoadSize = 25
    private val loadSize = 25

    fun browse(entityType: SeriesBrowseEntityType, mbid: String, authorized: Boolean = false) {
        val factory = SeriesBrowseDataSource.Factory(entityType, mbid, authorized)
        val config = buildPagedListConfig(loadSize, initialLoadSize)
        initPagedItems(config, factory)
    }

}