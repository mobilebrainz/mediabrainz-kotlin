package app.mediabrainz.ui.viewmodel.repository

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.mediabrainz.domain.model.Series
import app.mediabrainz.domain.repository.Resource
import app.mediabrainz.domain.repository.SeriesSearchRepository


class SeriesSearchViewModel : ViewModel() {

    private val seriesSearchRepository = SeriesSearchRepository()
    val seriesResource: MutableLiveData<Resource<List<Series>>> = MutableLiveData()

    private var query: String = ""

    fun searchSeries(query: String) {
        if (seriesResource.value != null || this.query != query) {
            this.query = query
            searchSeries()
        }
    }

    // retry when error
    fun searchSeries() {
        if (query != "") {
            seriesSearchRepository.search(seriesResource, query)
        }
    }

    override fun onCleared() {
        super.onCleared()
        seriesSearchRepository.cancelJob()
    }

}