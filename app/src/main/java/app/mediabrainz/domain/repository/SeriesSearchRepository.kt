package app.mediabrainz.domain.repository

import androidx.lifecycle.MutableLiveData
import app.mediabrainz.api.ApiRequestProvider
import app.mediabrainz.api.response.SeriesResponse
import app.mediabrainz.domain.mapper.PageMapper
import app.mediabrainz.domain.mapper.SeriesMapper
import app.mediabrainz.domain.model.Series
import app.mediabrainz.domain.parenthesesString


class SeriesSearchRepository : BaseApiRepository() {

    fun search(mutableLiveData: MutableLiveData<Resource<List<Series>>>, query: String) {
        val limit = 100
        call(mutableLiveData,
            {
                ApiRequestProvider.createSeriesSearchRequest()
                    .search(parenthesesString(query), limit, 0)
            },
            {
                PageMapper<SeriesResponse, Series> { SeriesMapper().mapTo(it) }.mapToList(getItems())
            }
        )
    }

}