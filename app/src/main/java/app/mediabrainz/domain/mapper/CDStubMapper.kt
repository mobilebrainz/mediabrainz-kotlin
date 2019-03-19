package app.mediabrainz.domain.mapper

import app.mediabrainz.api.response.CDStubResponse
import app.mediabrainz.domain.model.CDStub


class CDStubMapper {

    fun mapTo(response: CDStubResponse) = with(response) {
        val cdstub = CDStub(id, title ?: "")
        cdstub
    }

}