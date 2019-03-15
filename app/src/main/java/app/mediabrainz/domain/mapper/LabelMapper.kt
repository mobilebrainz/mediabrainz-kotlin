package app.mediabrainz.domain.mapper

import app.mediabrainz.api.response.LabelResponse
import app.mediabrainz.domain.model.Label


class LabelMapper {

    fun mapTo(response: LabelResponse) = with(response) {
        val label = Label(mbid, name)
        label
    }

    fun mapToList(responseList: List<LabelResponse>): List<Label> {
        val labels = ArrayList<Label>()
        for (response in responseList) {
            labels.add(mapTo(response))
        }
        return labels
    }
}