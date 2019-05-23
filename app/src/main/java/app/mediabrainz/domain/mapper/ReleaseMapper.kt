package app.mediabrainz.domain.mapper

import app.mediabrainz.api.response.LabelInfoResponse
import app.mediabrainz.api.response.MediaResponse
import app.mediabrainz.api.response.ReleaseResponse
import app.mediabrainz.domain.model.LabelInfo
import app.mediabrainz.domain.model.Media
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
                barcode ?: "",
                country ?: "",
                PageMapper<LabelInfoResponse, LabelInfo> { LabelInfoMapper().mapTo(it) }.mapToList(labelInfo),
                PageMapper<MediaResponse, Media> { MediaMapper().mapTo(it) }.mapToList(media)
            )
        }

}