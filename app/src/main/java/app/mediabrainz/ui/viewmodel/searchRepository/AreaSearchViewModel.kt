package app.mediabrainz.ui.viewmodel.searchRepository

import app.mediabrainz.domain.model.Area
import app.mediabrainz.domain.repository.searchRepository.AreaSearchRepository


class AreaSearchViewModel(val repo: AreaSearchRepository = AreaSearchRepository()) :
    BaseSearchViewModel<Area>(repo) {

    override fun search() {
        repo.search(result, query)
    }

}