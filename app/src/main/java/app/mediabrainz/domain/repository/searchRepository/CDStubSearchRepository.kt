package app.mediabrainz.domain.repository.searchRepository

import androidx.lifecycle.MutableLiveData
import app.mediabrainz.api.ApiRequestProvider
import app.mediabrainz.api.response.CDStubResponse
import app.mediabrainz.domain.mapper.CDStubMapper
import app.mediabrainz.domain.mapper.PageMapper
import app.mediabrainz.domain.model.CDStub
import app.mediabrainz.domain.parenthesesString
import app.mediabrainz.domain.repository.BaseApiRepository
import app.mediabrainz.domain.repository.Resource


class CDStubSearchRepository : BaseSearchRepository<CDStub>() {

    override fun search(mutableLiveData: MutableLiveData<Resource<List<CDStub>>>, query: String) {
        if (query.isNotBlank()) {
            val limit = 100
            call(mutableLiveData,
                {
                    ApiRequestProvider.createCDStubSearchRequest()
                        .search(parenthesesString(query), limit, 0)
                },
                {
                    PageMapper<CDStubResponse, CDStub> { CDStubMapper().mapTo(it) }.mapToList(items)
                }
            )
        }
    }

}
