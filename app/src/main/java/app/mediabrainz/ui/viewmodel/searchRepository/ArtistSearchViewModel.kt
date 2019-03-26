package app.mediabrainz.ui.viewmodel.searchRepository

import app.mediabrainz.domain.model.Artist
import app.mediabrainz.domain.repository.searchRepository.ArtistSearchRepository


class ArtistSearchViewModel(val repo: ArtistSearchRepository = ArtistSearchRepository()) :
    BaseSearchViewModel<Artist>(repo) {

    override fun search() {
        repo.search(result, query)
    }

}