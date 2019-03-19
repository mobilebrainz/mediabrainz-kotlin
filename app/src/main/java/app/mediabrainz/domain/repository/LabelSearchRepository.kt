package app.mediabrainz.domain.repository

import androidx.lifecycle.MutableLiveData
import app.mediabrainz.api.ApiRequestProvider
import app.mediabrainz.api.response.LabelResponse
import app.mediabrainz.domain.mapper.LabelMapper
import app.mediabrainz.domain.mapper.PageMapper
import app.mediabrainz.domain.model.Label
import app.mediabrainz.domain.parenthesesString


class LabelSearchRepository : BaseApiRepository() {

    fun search(mutableLiveData: MutableLiveData<Resource<List<Label>>>, query: String) {
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
