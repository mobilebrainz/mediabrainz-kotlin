package app.mediabrainz.ui.viewmodel.searchRepository

import app.mediabrainz.domain.model.ReleaseGroup
import app.mediabrainz.domain.repository.searchRepository.ReleaseGroupSearchRepository


class ReleaseGroupSearchViewModel(val repo: ReleaseGroupSearchRepository = ReleaseGroupSearchRepository()) :
    BaseSearchViewModel<ReleaseGroup>(repo) {

    private var artistQuery: String = ""

    override fun search(query: String) {
        artistQuery = ""
        super.search(query)
    }

    fun search(artist: String, query: String) {
        if (result.value == null || this.artistQuery != artist || this.query != query) {
            this.artistQuery = artist
            this.query = query
            search()
        }
    }

    override fun search() {
        repo.search(result, artistQuery, query)
    }

}