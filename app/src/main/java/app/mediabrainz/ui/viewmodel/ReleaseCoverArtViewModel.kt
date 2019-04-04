package app.mediabrainz.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.mediabrainz.domain.model.CoverArt
import app.mediabrainz.domain.repository.CoverArtRepository
import app.mediabrainz.domain.repository.Resource


class ReleaseCoverArtViewModel : ViewModel() {

    val repository: CoverArtRepository = CoverArtRepository()
    val result: MutableLiveData<Resource<List<CoverArt>>> = MutableLiveData()
    private var mbid: String = ""

    fun fetch(mbid: String) {
        if (result.value == null || this.mbid != mbid) {
            this.mbid = mbid
            fetch()
        }
    }

    fun fetch() {
        repository.getReleaseCoverArt(result, mbid)
    }

    override fun onCleared() {
        super.onCleared()
        repository.cancelJob()
    }

}