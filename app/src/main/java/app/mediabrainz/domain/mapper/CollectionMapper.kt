package app.mediabrainz.domain.mapper

import app.mediabrainz.api.response.CollectionResponse
import app.mediabrainz.domain.model.Collection


class CollectionMapper {

    fun mapTo(response: CollectionResponse) = with(response) {
        val collection = Collection(id, name)
        collection
    }

}