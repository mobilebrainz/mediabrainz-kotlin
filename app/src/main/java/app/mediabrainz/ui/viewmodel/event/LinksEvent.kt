package app.mediabrainz.ui.viewmodel.event

import androidx.lifecycle.ViewModel
import app.mediabrainz.domain.model.Relation
import app.mediabrainz.domain.model.Url
import app.mediabrainz.ui.core.viewmodel.SingleLiveEvent


class LinksEvent : ViewModel() {

    val urlRelations = SingleLiveEvent<List<Relation<Url>>>()

}