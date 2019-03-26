package app.mediabrainz.ui.viewmodel.searchRepository

import app.mediabrainz.domain.model.CDStub
import app.mediabrainz.domain.repository.searchRepository.CDStubSearchRepository


class CDStubSearchViewModel(val repo: CDStubSearchRepository = CDStubSearchRepository()) :
    BaseSearchViewModel<CDStub>(repo) {

    override fun search() {
        repo.search(result, query)
    }

}