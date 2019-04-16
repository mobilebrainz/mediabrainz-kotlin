package app.mediabrainz.domain.mapper

import app.mediabrainz.api.response.RatingResponse
import app.mediabrainz.domain.model.Rating


class RatingMapper {
    fun mapTo(response: RatingResponse?) =
        if (response == null) {
            Rating()
        } else {
            with(response) {
                Rating(value ?: 0.0f, votesCount ?: 0)
            }
        }

}