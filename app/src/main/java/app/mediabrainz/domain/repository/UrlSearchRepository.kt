package app.mediabrainz.domain.repository

import androidx.lifecycle.MutableLiveData
import app.mediabrainz.api.ApiRequestProvider
import app.mediabrainz.api.response.ArtistResponse
import app.mediabrainz.api.response.UrlResponse
import app.mediabrainz.domain.mapper.ArtistMapper
import app.mediabrainz.domain.mapper.PageMapper
import app.mediabrainz.domain.mapper.UrlMapper
import app.mediabrainz.domain.model.Artist
import app.mediabrainz.domain.model.Url
import app.mediabrainz.domain.parenthesesString


class UrlSearchRepository : BaseApiRepository() {

    fun search(mutableLiveData: MutableLiveData<Resource<List<Url>>>, query: String) {
        val limit = 100
        call(mutableLiveData,
            {
                ApiRequestProvider.createUrlSearchRequest()
                    .search(parenthesesString(query), limit, 0)
            },
            {
                PageMapper<UrlResponse, Url> { UrlMapper().mapTo(it) }.mapToList(getItems())
            }
        )
    }

}
