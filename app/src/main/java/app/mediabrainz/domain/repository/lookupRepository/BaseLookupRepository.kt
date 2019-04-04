package app.mediabrainz.domain.repository.lookupRepository

import androidx.lifecycle.MutableLiveData
import app.mediabrainz.domain.model.Artist
import app.mediabrainz.domain.repository.BaseApiRepository
import app.mediabrainz.domain.repository.Resource


abstract class BaseLookupRepository<OUT> : BaseApiRepository() {

    abstract fun lookup(mutableLiveData: MutableLiveData<Resource<OUT>>, mbid: String)

    open fun authLookup(mutableLiveData: MutableLiveData<Resource<OUT>>, mbid: String) {
        lookup(mutableLiveData, mbid)
    }

}