package app.mediabrainz.domain.mapper

import app.mediabrainz.api.response.CoverArtArchiveResponse
import app.mediabrainz.domain.model.CoverArtArchive


class CoverArtArchiveMapper {

    fun mapTo(response: CoverArtArchiveResponse?): CoverArtArchive =
        if (response == null) CoverArtArchive()
        else with(response) {
            CoverArtArchive(
                back ?: false,
                darkened ?: false,
                front ?: false,
                artwork ?: false,
                count ?: 0
            )
        }

}