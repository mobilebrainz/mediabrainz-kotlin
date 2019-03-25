package app.mediabrainz.domain.repository.searchRepository

import androidx.lifecycle.MutableLiveData
import app.mediabrainz.api.ApiRequestProvider
import app.mediabrainz.api.response.ArtistResponse
import app.mediabrainz.domain.mapper.ArtistMapper
import app.mediabrainz.domain.mapper.PageMapper
import app.mediabrainz.domain.model.Artist
import app.mediabrainz.domain.parenthesesString
import app.mediabrainz.domain.repository.BaseApiRepository
import app.mediabrainz.domain.repository.Resource


class ArtistSearchRepository : BaseApiRepository() {

    fun search(mutableLiveData: MutableLiveData<Resource<List<Artist>>>, query: String) {
        val limit = 100
        call(mutableLiveData,
            {
                ApiRequestProvider.createArtistSearchRequest()
                    .search(parenthesesString(query), limit, 0)
            },
            {
                PageMapper<ArtistResponse, Artist> { ArtistMapper().mapTo(it) }.mapToList(getItems())
            }
        )
    }

}
