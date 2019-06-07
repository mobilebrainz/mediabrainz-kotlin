package app.mediabrainz.ui.viewmodel.fragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.mediabrainz.domain.model.Artist


class ArtistTagsPagerFragmentVM : ViewModel() {

    val artistld = MutableLiveData<Artist>()
}