package app.mediabrainz.ui.viewmodel.repository

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.mediabrainz.domain.model.Url
import app.mediabrainz.domain.repository.Resource
import app.mediabrainz.domain.repository.UrlSearchRepository


class UrlSearchViewModel : ViewModel() {

    private val urlSearchRepository = UrlSearchRepository()
    val urlResource: MutableLiveData<Resource<List<Url>>> = MutableLiveData()

    private var query: String = ""

    fun searchUrl(query: String) {
        if (urlResource.value != null || this.query != query) {
            this.query = query
            searchUrl()
        }
    }

    // retry when error
    fun searchUrl() {
        if (query != "") {
            urlSearchRepository.search(urlResource, query)
        }
    }

    override fun onCleared() {
        super.onCleared()
        urlSearchRepository.cancelJob()
    }

}