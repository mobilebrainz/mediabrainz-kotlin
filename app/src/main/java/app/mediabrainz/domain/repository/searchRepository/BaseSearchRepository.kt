package app.mediabrainz.domain.repository.searchRepository

import androidx.lifecycle.MutableLiveData
import app.mediabrainz.domain.repository.BaseApiRepository
import app.mediabrainz.domain.repository.Resource


abstract class BaseSearchRepository<OUT> : BaseApiRepository() {

    abstract fun search(mutableLiveData: MutableLiveData<Resource<List<OUT>>>, query: String)

}