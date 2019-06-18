package app.mediabrainz.domain.repository.xmlRepository

import androidx.lifecycle.MutableLiveData
import app.mediabrainz.api.XMLRequestProvider
import app.mediabrainz.api.xml.entity.UserTagXML
import app.mediabrainz.domain.OAuthManager
import app.mediabrainz.domain.mapper.MetadataMapper
import app.mediabrainz.domain.repository.BaseApiRepository
import app.mediabrainz.domain.repository.Resource
import app.mediabrainz.ui.App


class PostArtistTagsRepository : BaseApiRepository() {

    fun postArtistTags(
        mutableLiveData: MutableLiveData<Resource<Boolean>>,
        artistMbid: String,
        vararg tags: UserTagXML
    ) {
        OAuthManager.accessToken?.token?.let { token ->
            call(
                mutableLiveData,
                { XMLRequestProvider.postArtistTags(App.CLIENT, token, artistMbid, *tags) },
                { MetadataMapper().postOk(this) },
                true
            )
        }
    }

}