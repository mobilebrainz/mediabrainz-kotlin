package app.mediabrainz.domain.mapper

import app.mediabrainz.api.response.CollectionResponse
import app.mediabrainz.domain.model.Collection


class CollectionMapper {

    fun mapTo(response: CollectionResponse): Collection =
        with(response) {
            Collection(id, name)
        }

}