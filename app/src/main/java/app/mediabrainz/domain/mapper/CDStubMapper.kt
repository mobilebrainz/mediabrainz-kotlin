package app.mediabrainz.domain.mapper

import app.mediabrainz.api.response.CDStubResponse
import app.mediabrainz.domain.model.CDStub


class CDStubMapper {

    fun mapTo(response: CDStubResponse): CDStub {
        val cdstub = CDStub(
            response.id,
            response.title ?: ""
        )
        return cdstub
    }

    fun mapToList(responseList: List<CDStubResponse>): List<CDStub> {
        val cdstubs = ArrayList<CDStub>()
        for (response in responseList) {
            cdstubs.add(mapTo(response))
        }
        return cdstubs
    }
}