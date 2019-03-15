package app.mediabrainz.domain.mapper

import app.mediabrainz.api.response.AnnotationResponse
import app.mediabrainz.domain.model.Annotation


class AnnotationMapper {

    fun mapTo(response: AnnotationResponse) = with(response) {
        val annotation = Annotation(id, name ?: "", text ?: "")
        annotation
    }

    fun mapToList(responseList: List<AnnotationResponse>): List<Annotation> {
        val annotations = ArrayList<Annotation>()
        for (response in responseList) {
            annotations.add(mapTo(response))
        }
        return annotations
    }
}