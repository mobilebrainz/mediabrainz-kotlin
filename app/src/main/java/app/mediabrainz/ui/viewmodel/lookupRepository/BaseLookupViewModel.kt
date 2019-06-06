package app.mediabrainz.ui.viewmodel.lookupRepository

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.mediabrainz.domain.repository.Resource
import app.mediabrainz.domain.repository.Resource.Status.ERROR
import app.mediabrainz.domain.repository.lookupRepository.BaseLookupRepository


abstract class BaseLookupViewModel<T>(val repository: BaseLookupRepository<T>) : ViewModel() {

    val result: MutableLiveData<Resource<T>> = MutableLiveData()

    protected var mbid: String = ""
    protected var oauth = false

    open fun lookup() {
        if (oauth) repository.authLookup(result, mbid)
        else repository.lookup(result, mbid)
    }

    open fun lookup(mbid: String, authorized: Boolean = false) {
        if (result.value == null || result.value!!.status == ERROR || this.mbid != mbid || this.oauth != authorized) {
            refresh(mbid, authorized)
        }
    }

    open fun refresh(mbid: String, authorized: Boolean = false) {
        this.mbid = mbid
        this.oauth = authorized
        lookup()
    }

    override fun onCleared() {
        super.onCleared()
        repository.cancelJob()
    }

}