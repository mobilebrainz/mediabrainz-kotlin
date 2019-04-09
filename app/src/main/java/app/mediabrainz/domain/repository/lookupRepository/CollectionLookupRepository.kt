package app.mediabrainz.domain.repository.lookupRepository

import androidx.lifecycle.MutableLiveData
import app.mediabrainz.api.ApiRequestProvider
import app.mediabrainz.domain.mapper.CollectionMapper
import app.mediabrainz.domain.model.Collection
import app.mediabrainz.domain.repository.Resource


class CollectionLookupRepository : BaseLookupRepository<Collection>() {

    override fun lookup(mutableLiveData: MutableLiveData<Resource<Collection>>, mbid: String) {
        if (mbid.isNotBlank()) {
            call(
                mutableLiveData,
                { ApiRequestProvider.createCollectionLookupRequest(mbid).lookup() },
                { CollectionMapper().mapTo(this) },
                false
            )
        }
    }

}