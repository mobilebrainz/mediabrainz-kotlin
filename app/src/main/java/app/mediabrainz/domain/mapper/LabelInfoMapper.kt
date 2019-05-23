package app.mediabrainz.domain.mapper

import app.mediabrainz.api.response.LabelInfoResponse
import app.mediabrainz.api.response.TagResponse
import app.mediabrainz.domain.model.LabelInfo
import app.mediabrainz.domain.model.Tag


class LabelInfoMapper {

    fun mapTo(response: LabelInfoResponse): LabelInfo =
        with(response) {
            LabelInfo(
                catalogNumber ?: "",
                LabelMapper().mapTo(label)
            )
        }
}