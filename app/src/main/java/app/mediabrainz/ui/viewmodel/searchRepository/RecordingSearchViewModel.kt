package app.mediabrainz.ui.viewmodel.searchRepository

import app.mediabrainz.domain.model.Recording
import app.mediabrainz.domain.repository.searchRepository.RecordingSearchRepository


class RecordingSearchViewModel(val repo: RecordingSearchRepository = RecordingSearchRepository()) :
    BaseSearchViewModel<Recording>(repo) {

    private var artistQuery: String = ""
    private var releaseQuery: String = ""

    override fun search(query: String) {
        artistQuery = ""
        releaseQuery = ""
        super.search(query)
    }

    fun search(artist: String, release: String, recording: String) {
        if (result.value == null ||
            artistQuery != artist || releaseQuery != release || query != recording
        ) {
            this.artistQuery = artist
            this.releaseQuery = release
            this.query = recording
            search()
        }
    }

    override fun search() {
        repo.search(result, artistQuery, releaseQuery, query)
    }

}