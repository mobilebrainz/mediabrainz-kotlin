package app.mediabrainz.ui.viewmodel.repository

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.mediabrainz.domain.model.Tag
import app.mediabrainz.domain.repository.Resource
import app.mediabrainz.domain.repository.TagSearchRepository


class TagSearchViewModel : ViewModel() {

    private val tagSearchRepository = TagSearchRepository()
    val tagResource: MutableLiveData<Resource<List<Tag>>> = MutableLiveData()

    private var query: String = ""

    fun searchTag(query: String) {
        if (tagResource.value != null || this.query != query) {
            this.query = query
            searchTag()
        }
    }

    // retry when error
    fun searchTag() {
        if (query != "") {
            tagSearchRepository.search(tagResource, query)
        }
    }

    override fun onCleared() {
        super.onCleared()
        tagSearchRepository.cancelJob()
    }

}