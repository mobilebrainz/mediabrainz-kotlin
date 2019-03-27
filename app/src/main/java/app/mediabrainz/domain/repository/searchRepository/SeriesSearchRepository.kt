package app.mediabrainz.domain.repository.searchRepository

import androidx.lifecycle.MutableLiveData
import app.mediabrainz.api.ApiRequestProvider
import app.mediabrainz.api.response.SeriesResponse
import app.mediabrainz.domain.mapper.PageMapper
import app.mediabrainz.domain.mapper.SeriesMapper
import app.mediabrainz.domain.model.Series
import app.mediabrainz.domain.parenthesesString
import app.mediabrainz.domain.repository.BaseApiRepository
import app.mediabrainz.domain.repository.Resource


class SeriesSearchRepository : BaseSearchRepository<Series>() {

    override fun search(mutableLiveData: MutableLiveData<Resource<List<Series>>>, query: String) {
        if (query.isNotBlank()) {
            val limit = 100
            call(mutableLiveData,
                {
                    ApiRequestProvider.createSeriesSearchRequest()
                        .search(parenthesesString(query), limit, 0)
                },
                {
                    PageMapper<SeriesResponse, Series> { SeriesMapper().mapTo(it) }.mapToList(items)
                }
            )
        }
    }

}