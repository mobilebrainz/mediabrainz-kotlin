package app.mediabrainz.domain.repository

import androidx.lifecycle.MutableLiveData
import app.mediabrainz.api.ApiRequestProvider
import app.mediabrainz.api.response.TagResponse
import app.mediabrainz.domain.mapper.PageMapper
import app.mediabrainz.domain.mapper.TagMapper
import app.mediabrainz.domain.model.Tag
import app.mediabrainz.domain.parenthesesString


class TagSearchRepository : BaseApiRepository() {

    fun search(mutableLiveData: MutableLiveData<Resource<List<Tag>>>, query: String) {
        val limit = 100
        call(mutableLiveData,
            {
                ApiRequestProvider.createTagSearchRequest()
                    .search(parenthesesString(query), limit, 0)
            },
            {
                PageMapper<TagResponse, Tag> { TagMapper().mapTo(it) }.mapToList(getItems())
            }
        )
    }

}