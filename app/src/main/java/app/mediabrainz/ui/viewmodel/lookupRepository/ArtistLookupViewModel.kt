package app.mediabrainz.ui.viewmodel.lookupRepository

import androidx.lifecycle.MutableLiveData
import app.mediabrainz.domain.model.Artist
import app.mediabrainz.domain.repository.Resource
import app.mediabrainz.domain.repository.lookupRepository.ArtistLookupRepository


class ArtistLookupViewModel(val repo: ArtistLookupRepository = ArtistLookupRepository()) :
    BaseLookupViewModel<Artist>(repo) {

    val artistTags: MutableLiveData<Resource<Artist>> = MutableLiveData()

    fun lookupTags(mbid: String) {
        repo.lookupTags(artistTags, mbid)
    }

}
