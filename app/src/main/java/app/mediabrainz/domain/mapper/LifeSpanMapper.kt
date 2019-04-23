package app.mediabrainz.domain.mapper

import app.mediabrainz.api.response.LifeSpanResponse
import app.mediabrainz.domain.model.LifeSpan


class LifeSpanMapper {

    fun mapTo(response: LifeSpanResponse?): LifeSpan {
        if (response == null) return LifeSpan()

        with(response) {
            return LifeSpan(
                begin ?: "",
                end ?: "",
                ended ?: false
            )
        }
    }
}