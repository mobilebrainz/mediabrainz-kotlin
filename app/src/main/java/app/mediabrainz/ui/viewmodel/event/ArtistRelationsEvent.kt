package app.mediabrainz.ui.viewmodel.event

import androidx.lifecycle.ViewModel
import app.mediabrainz.domain.model.Artist
import app.mediabrainz.domain.model.Relation
import app.mediabrainz.domain.model.Url
import app.mediabrainz.ui.core.viewmodel.SingleLiveEvent


class ArtistRelationsEvent : ViewModel() {

    val artistRelations = SingleLiveEvent<List<Relation<Artist>>>()

}