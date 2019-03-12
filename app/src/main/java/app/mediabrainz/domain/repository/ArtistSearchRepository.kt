package app.mediabrainz.domain.repository

import androidx.lifecycle.MutableLiveData
import app.mediabrainz.api.response.ArtistSearchResponse
import app.mediabrainz.api.service.ApiServiceProvider
import app.mediabrainz.domain.mapper.ArtistMapper
import app.mediabrainz.domain.model.Artist


class ArtistSearchRepository : BaseRepository() {

    fun search(
        mutableLiveData: MutableLiveData<Resource<List<Artist>?>>,
        artist: String,
        limit: Int,
        offset: Int
    ) {
        mutableLiveData.value = Resource.loading(null)
        search503(mutableLiveData, artist, limit, offset)
    }

    fun search503(
        mutableLiveData: MutableLiveData<Resource<List<Artist>?>>,
        artist: String,
        limit: Int,
        offset: Int
    ) {
        val request = ApiServiceProvider.createArtistSearchService().search(artist, limit, offset)
        call(mutableLiveData, request, { search503(mutableLiveData, artist, limit, offset) }, this::map)
    }

    fun map(source: ArtistSearchResponse) = ArtistMapper().mapToArtists(source.artists)

}