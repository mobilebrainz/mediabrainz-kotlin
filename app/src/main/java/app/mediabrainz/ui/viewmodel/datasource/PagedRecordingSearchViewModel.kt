package app.mediabrainz.ui.viewmodel.datasource

import app.mediabrainz.domain.datasource.RecordingSearchDataSource
import app.mediabrainz.domain.model.Recording


class PagedRecordingSearchViewModel : BaseDataSourceViewModel<Recording>() {

    private val initialLoadSize = 25
    private val loadSize = 25

    fun search(recording: String) {
        search("", "", recording)
    }

    fun search(artist: String, release: String, recording: String) {
        val factory = RecordingSearchDataSource.Factory(artist, release, recording)
        val config = buildPagedListConfig(loadSize, initialLoadSize)
        initPagedItems(config, factory)
    }

}