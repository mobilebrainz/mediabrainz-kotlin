package app.mediabrainz.domain.mapper

import app.mediabrainz.api.response.TagResponse
import app.mediabrainz.domain.model.Tag


class TagMapper {

    fun mapTo(response: TagResponse) = with(response) {
        val tag = Tag(name)
        tag
    }

}