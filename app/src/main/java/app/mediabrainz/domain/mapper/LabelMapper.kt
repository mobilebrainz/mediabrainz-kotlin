package app.mediabrainz.domain.mapper

import app.mediabrainz.api.response.LabelResponse
import app.mediabrainz.domain.model.Label


class LabelMapper {

    fun mapTo(response: LabelResponse): Label {
        val label = Label(
            response.mbid,
            response.name
        )
        return label
    }

    fun mapToList(responseList: List<LabelResponse>): List<Label> {
        val labels = ArrayList<Label>()
        for (response in responseList) {
            labels.add(mapTo(response))
        }
        return labels
    }
}