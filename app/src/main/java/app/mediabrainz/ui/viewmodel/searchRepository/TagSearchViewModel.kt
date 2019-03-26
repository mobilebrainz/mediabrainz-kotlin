package app.mediabrainz.ui.viewmodel.searchRepository

import app.mediabrainz.domain.model.Tag
import app.mediabrainz.domain.repository.searchRepository.TagSearchRepository


class TagSearchViewModel(val repo: TagSearchRepository = TagSearchRepository()) :
    BaseSearchViewModel<Tag>(repo) {

    override fun search() {
        repo.search(result, query)
    }

}