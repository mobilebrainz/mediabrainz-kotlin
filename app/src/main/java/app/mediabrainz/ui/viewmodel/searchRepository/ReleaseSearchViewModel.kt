package app.mediabrainz.ui.viewmodel.searchRepository

import app.mediabrainz.domain.model.Release
import app.mediabrainz.domain.repository.searchRepository.ReleaseSearchRepository


class ReleaseSearchViewModel(val repo: ReleaseSearchRepository = ReleaseSearchRepository()) :
    BaseSearchViewModel<Release>(repo) {

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