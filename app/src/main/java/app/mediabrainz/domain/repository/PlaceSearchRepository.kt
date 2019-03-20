package app.mediabrainz.domain.repository

import androidx.lifecycle.MutableLiveData
import app.mediabrainz.api.ApiRequestProvider
import app.mediabrainz.api.response.AnnotationResponse
import app.mediabrainz.api.response.PlaceResponse
import app.mediabrainz.domain.mapper.PageMapper
import app.mediabrainz.domain.mapper.PlaceMapper
import app.mediabrainz.domain.model.Place
import app.mediabrainz.domain.parenthesesString


class PlaceSearchRepository : BaseApiRepository() {

    fun search(mutableLiveData: MutableLiveData<Resource<List<Place>>>, query: String) {
        val limit = 100
        call(mutableLiveData,
            {
                ApiRequestProvider.createPlaceSearchRequest()
                    .search(parenthesesString(query), limit, 0)
            },
            {
                PageMapper<PlaceResponse, Place> { PlaceMapper().mapTo(it) }.mapToList(getItems())
            }
        )
    }

}