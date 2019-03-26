package app.mediabrainz.ui.viewmodel.lookupRepository

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.mediabrainz.domain.repository.BaseApiRepository
import app.mediabrainz.domain.repository.Resource


abstract class BaseLookupViewModel<T>(val repository: BaseApiRepository) : ViewModel() {

    val result: MutableLiveData<Resource<T>> = MutableLiveData()

    protected var mbid: String = ""

    abstract fun lookup()

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