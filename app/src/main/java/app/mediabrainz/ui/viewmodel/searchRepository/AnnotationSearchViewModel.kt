package app.mediabrainz.ui.viewmodel.searchRepository

import app.mediabrainz.domain.model.Annotation
import app.mediabrainz.domain.repository.searchRepository.AnnotationSearchRepository


class AnnotationSearchViewModel(val repo: AnnotationSearchRepository = AnnotationSearchRepository()) :
    BaseSearchViewModel<Annotation>(repo)
