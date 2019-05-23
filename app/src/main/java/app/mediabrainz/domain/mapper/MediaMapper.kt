package app.mediabrainz.domain.mapper

import app.mediabrainz.api.response.MediaResponse
import app.mediabrainz.domain.model.Media
import app.mediabrainz.domain.model.MediaFormatType


class MediaMapper {

    fun mapTo(response: MediaResponse): Media =
        with(response) {
            Media(
                title,
                MediaFormatType.typeOf(format ?: ""),
                trackCount ?: 0
            )
        }

}