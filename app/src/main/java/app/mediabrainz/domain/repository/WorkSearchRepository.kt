package app.mediabrainz.domain.repository

import androidx.lifecycle.MutableLiveData
import app.mediabrainz.api.ApiRequestProvider
import app.mediabrainz.api.response.ArtistResponse
import app.mediabrainz.api.response.UrlResponse
import app.mediabrainz.api.response.WorkResponse
import app.mediabrainz.domain.mapper.ArtistMapper
import app.mediabrainz.domain.mapper.PageMapper
import app.mediabrainz.domain.mapper.UrlMapper
import app.mediabrainz.domain.mapper.WorkMapper
import app.mediabrainz.domain.model.Artist
import app.mediabrainz.domain.model.Url
import app.mediabrainz.domain.model.Work
import app.mediabrainz.domain.parenthesesString


class WorkSearchRepository : BaseApiRepository() {

    fun search(mutableLiveData: MutableLiveData<Resource<List<Work>>>, query: String) {
        val limit = 100
        call(mutableLiveData,
            {
                ApiRequestProvider.createWorkSearchRequest()
                    .search(parenthesesString(query), limit, 0)
            },
            {
                PageMapper<WorkResponse, Work> { WorkMapper().mapTo(it) }.mapToList(getItems())
            }
        )
    }

}
