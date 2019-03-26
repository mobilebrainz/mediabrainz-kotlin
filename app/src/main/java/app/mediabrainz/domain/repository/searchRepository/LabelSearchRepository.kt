package app.mediabrainz.domain.repository.searchRepository

import androidx.lifecycle.MutableLiveData
import app.mediabrainz.api.ApiRequestProvider
import app.mediabrainz.api.response.LabelResponse
import app.mediabrainz.domain.mapper.LabelMapper
import app.mediabrainz.domain.mapper.PageMapper
import app.mediabrainz.domain.model.Label
import app.mediabrainz.domain.parenthesesString
import app.mediabrainz.domain.repository.BaseApiRepository
import app.mediabrainz.domain.repository.Resource


class LabelSearchRepository : BaseSearchRepository<Label>() {

    override fun search(mutableLiveData: MutableLiveData<Resource<List<Label>>>, query: String) {
        if (query.isNotBlank()) {
            val limit = 100
            call(mutableLiveData,
                {
                    ApiRequestProvider.createLabelSearchRequest()
                        .search(parenthesesString(query), limit, 0)
                },
                {
                    PageMapper<LabelResponse, Label> { LabelMapper().mapTo(it) }.mapToList(getItems())
                }
            )
        }
    }

}
