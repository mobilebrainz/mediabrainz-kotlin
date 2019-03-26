package app.mediabrainz.ui.viewmodel.searchRepository

import app.mediabrainz.domain.model.Work
import app.mediabrainz.domain.repository.searchRepository.WorkSearchRepository


class WorkSearchViewModel(val repo: WorkSearchRepository = WorkSearchRepository()) :
    BaseSearchViewModel<Work>(repo) {

    override fun search() {
        repo.search(result, query)
    }

}