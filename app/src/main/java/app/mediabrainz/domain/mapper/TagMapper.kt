package app.mediabrainz.domain.mapper

import app.mediabrainz.api.response.TagResponse
import app.mediabrainz.domain.model.Tag
import app.mediabrainz.domain.model.Tagged


class TagMapper {

    fun mapTo(response: TagResponse): Tag =
        with(response) {
            Tag(name)
        }

    fun filterTagged(tagged: Tagged) {
        with(tagged) {
            tags = tags.filter { !genres.contains(it) }
            userTags = userTags.filter { !userGenres.contains(it) }
        }
    }
}