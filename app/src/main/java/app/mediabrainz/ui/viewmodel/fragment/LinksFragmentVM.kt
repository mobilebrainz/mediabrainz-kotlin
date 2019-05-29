package app.mediabrainz.ui.viewmodel.fragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.mediabrainz.domain.model.Relation
import app.mediabrainz.domain.model.Url


class LinksFragmentVM : ViewModel() {

    val urlRelations = MutableLiveData<List<Relation<Url>>>()
}