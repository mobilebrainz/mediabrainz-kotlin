package app.mediabrainz.domain.repository.searchRepository

import androidx.lifecycle.MutableLiveData
import app.mediabrainz.api.ApiRequestProvider
import app.mediabrainz.api.response.UrlResponse
import app.mediabrainz.domain.mapper.PageMapper
import app.mediabrainz.domain.mapper.UrlMapper
import app.mediabrainz.domain.model.Url
import app.mediabrainz.domain.parenthesesString
import app.mediabrainz.domain.repository.BaseApiRepository
import app.mediabrainz.domain.repository.Resource


class UrlSearchRepository : BaseSearchRepository<Url>() {

    override fun search(mutableLiveData: MutableLiveData<Resource<List<Url>>>, query: String) {
        if (query.isNotBlank()) {
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

}
