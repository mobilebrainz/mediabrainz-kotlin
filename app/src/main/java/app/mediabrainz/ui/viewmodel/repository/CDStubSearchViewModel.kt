package app.mediabrainz.ui.viewmodel.repository

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.mediabrainz.domain.model.CDStub
import app.mediabrainz.domain.repository.CDStubSearchRepository
import app.mediabrainz.domain.repository.Resource


class CDStubSearchViewModel : ViewModel() {

    private val cdStubSearchRepository = CDStubSearchRepository()
    val cdStubResource: MutableLiveData<Resource<List<CDStub>>> = MutableLiveData()

    private var query: String = ""

    fun searchCDStub(query: String) {
        if (cdStubResource.value != null || this.query != query) {
            this.query = query
            searchCDStub()
        }
    }

    // retry when error
    fun searchCDStub() {
        if (query != "") {
            cdStubSearchRepository.search(cdStubResource, query)
        }
    }

    override fun onCleared() {
        super.onCleared()
        cdStubSearchRepository.cancelJob()
    }

}