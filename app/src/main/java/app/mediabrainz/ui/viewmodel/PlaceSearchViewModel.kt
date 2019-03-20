package app.mediabrainz.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.mediabrainz.domain.model.Place
import app.mediabrainz.domain.repository.PlaceSearchRepository
import app.mediabrainz.domain.repository.Resource


class PlaceSearchViewModel : ViewModel() {

    private val placeSearchRepository = PlaceSearchRepository()
    val placeResource: MutableLiveData<Resource<List<Place>>> = MutableLiveData()

    private var query: String = ""

    fun searchPlace(query: String) {
        if (placeResource.value != null || this.query != query) {
            this.query = query
            searchPlace()
        }
    }

    // retry when error
    fun searchPlace() {
        if (query != "") {
            placeSearchRepository.search(placeResource, query)
        }
    }

    override fun onCleared() {
        super.onCleared()
        placeSearchRepository.cancelJob()
    }

}