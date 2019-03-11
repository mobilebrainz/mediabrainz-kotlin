package app.mediabrainz.ui.viewmodel

import androidx.lifecycle.ViewModel
import app.mediabrainz.domain.repository.ArtistSearchRepository


class SearchViewModel : ViewModel() {

    //var artistsResource: MutableLiveData<Resource<ArtistSearchResponse?>> = MutableLiveData()

    fun fetchArtists(artist: String, limit: Int, offset: Int) = ArtistSearchRepository().search(artist, limit, offset)

}