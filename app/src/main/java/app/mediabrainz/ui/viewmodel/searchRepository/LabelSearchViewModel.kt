package app.mediabrainz.ui.viewmodel.searchRepository

import app.mediabrainz.domain.model.Label
import app.mediabrainz.domain.repository.searchRepository.LabelSearchRepository


class LabelSearchViewModel(val repo: LabelSearchRepository = LabelSearchRepository()) :
    BaseSearchViewModel<Label>(repo) {

    override fun search() {
        repo.search(result, query)
    }

}