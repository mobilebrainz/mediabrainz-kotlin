package app.mediabrainz.ui.viewmodel.lookupRepository

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.mediabrainz.domain.repository.Resource
import app.mediabrainz.domain.repository.lookupRepository.BaseLookupRepository


abstract class BaseLookupViewModel<T>(val repository: BaseLookupRepository<T>) : ViewModel() {

    val result: MutableLiveData<Resource<T>> = MutableLiveData()

    protected var mbid: String = ""

    open fun lookup() {
        repository.lookup(result, mbid)
    }

    open fun lookup(mbid: String) {
        if (result.value == null || this.mbid != mbid) {
            this.mbid = mbid
            lookup()
        }
    }

    override fun onCleared() {
        super.onCleared()
        repository.cancelJob()
    }

}