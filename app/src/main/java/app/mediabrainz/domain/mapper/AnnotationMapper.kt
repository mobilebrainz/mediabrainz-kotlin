package app.mediabrainz.domain.mapper

import app.mediabrainz.api.response.AnnotationResponse
import app.mediabrainz.domain.model.Annotation


class AnnotationMapper {

    fun mapTo(response: AnnotationResponse): Annotation =
        with(response) {
            Annotation(
                id,
                name ?: "",
                text ?: ""
            )
        }

}