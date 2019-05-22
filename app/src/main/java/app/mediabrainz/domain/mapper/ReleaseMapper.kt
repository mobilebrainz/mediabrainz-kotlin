package app.mediabrainz.domain.mapper

import app.mediabrainz.api.response.ReleaseResponse
import app.mediabrainz.domain.model.Release
import app.mediabrainz.domain.model.ReleaseStatus


class ReleaseMapper {

    fun mapTo(response: ReleaseResponse): Release =
        with(response) {
            Release(
                mbid,
                title,
                ReleaseStatus.typeOf(status ?: ""),
                date ?: "",
                barcode ?: ""
            )
        }

}