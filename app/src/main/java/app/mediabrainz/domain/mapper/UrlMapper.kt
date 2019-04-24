package app.mediabrainz.domain.mapper

import app.mediabrainz.api.response.UrlResponse
import app.mediabrainz.domain.model.Url


class UrlMapper {

    fun mapTo(response: UrlResponse): Url =
        with(response) {
            Url(mbid, resource)
        }

}