package app.mediabrainz.domain.repository.lookupRepository

import androidx.lifecycle.MutableLiveData
import app.mediabrainz.api.ApiRequestProvider
import app.mediabrainz.domain.mapper.SeriesMapper
import app.mediabrainz.domain.model.Series
import app.mediabrainz.domain.repository.BaseApiRepository
import app.mediabrainz.domain.repository.Resource


class SeriesLookupRepository : BaseLookupRepository<Series>() {

    override fun lookup(mutableLiveData: MutableLiveData<Resource<Series>>, mbid: String) {
        if (mbid.isNotBlank()) {
            call(mutableLiveData,
                { ApiRequestProvider.createSeriesLookupRequest(mbid).lookup() },
                { SeriesMapper().mapTo(this) })
        }
    }

}