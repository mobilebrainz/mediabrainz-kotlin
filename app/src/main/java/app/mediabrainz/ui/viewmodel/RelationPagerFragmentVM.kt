package app.mediabrainz.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.mediabrainz.domain.model.Artist
import app.mediabrainz.domain.model.Relation
import app.mediabrainz.domain.model.Url


class RelationPagerFragmentVM : ViewModel() {

    val artistRelations = MutableLiveData<List<Relation<Artist>>>()
}