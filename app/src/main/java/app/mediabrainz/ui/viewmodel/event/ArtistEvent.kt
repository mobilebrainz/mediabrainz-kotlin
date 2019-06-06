package app.mediabrainz.ui.viewmodel.event

import androidx.lifecycle.ViewModel
import app.mediabrainz.domain.model.Artist
import app.mediabrainz.ui.core.viewmodel.SingleLiveEvent


class ArtistEvent : ViewModel() {

    val artist = SingleLiveEvent<Artist>()

}