package app.mediabrainz.ui.viewmodel.lookupRepository

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.mediabrainz.domain.repository.Resource
import app.mediabrainz.domain.repository.lookupRepository.BaseLookupRepository


abstract class BaseLookupViewModel<T>(val repository: BaseLookupRepository<T>) : ViewModel() {

    val result: MutableLiveData<Resource<T>> = MutableLiveData()

    protected var mbid: String = ""
    protected var oauth = false

    open fun lookup() {
        repository.lookup(result, mbid)
    }

    open fun authLookup() {
        repository.authLookup(result, mbid)
    }

    open fun lookup(mbid: String) {
        if (result.value == null || this.mbid != mbid || oauth) {
            this.mbid = mbid
            oauth = false
            lookup()
        }
    }

    open fun authLookup(mbid: String) {
        if (result.value == null || this.mbid != mbid || !oauth) {
            this.mbid = mbid
            oauth = true
            authLookup()
        }
    }

    override fun onCleared() {
        super.onCleared()
        repository.cancelJob()
    }

}