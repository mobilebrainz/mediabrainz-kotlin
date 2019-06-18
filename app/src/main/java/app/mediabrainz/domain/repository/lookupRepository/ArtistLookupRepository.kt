package app.mediabrainz.domain.repository.lookupRepository

import androidx.lifecycle.MutableLiveData
import app.mediabrainz.api.ApiRequestProvider
import app.mediabrainz.api.lookupbrowse.RelsType.ARTIST_RELS
import app.mediabrainz.api.lookupbrowse.RelsType.URL_RELS
import app.mediabrainz.api.lookuprequest.ArtistLookupIncType.*
import app.mediabrainz.domain.OAuthManager
import app.mediabrainz.domain.mapper.ArtistMapper
import app.mediabrainz.domain.model.Artist
import app.mediabrainz.domain.repository.Resource


class ArtistLookupRepository : BaseLookupRepository<Artist>() {

    val incs = arrayOf(RATINGS, GENRES, TAGS, RELEASE_GROUPS)
    val rels = arrayOf(URL_RELS, ARTIST_RELS)

    override fun lookup(mutableLiveData: MutableLiveData<Resource<Artist>>, mbid: String) {
        if (mbid.isNotBlank()) {
            call(
                mutableLiveData,
                {
                    ApiRequestProvider.createArtistLookupRequest(mbid)
                        .addIncs(*incs).addRels(*rels).lookup()
                },
                { ArtistMapper().mapTo(this) },
                false
            )
        }
    }

    override fun authLookup(mutableLiveData: MutableLiveData<Resource<Artist>>, mbid: String) {
        if (mbid.isNotBlank()) {
            call(
                mutableLiveData,
                {
                    ApiRequestProvider.createArtistLookupRequest(mbid)
                        .addIncs(*incs)
                        .addIncs(USER_RATINGS, USER_GENRES, USER_TAGS)
                        .addRels(*rels)
                        .addAccessToken(OAuthManager.accessToken?.token ?: "")
                        .lookup()
                },
                { ArtistMapper().mapTo(this) },
                true
            )
        }
    }

    fun lookupTags(mutableLiveData: MutableLiveData<Resource<Artist>>, mbid: String) {
        if (mbid.isNotBlank()) {
            call(
                mutableLiveData,
                {
                    ApiRequestProvider.createArtistLookupRequest(mbid)
                        .addIncs(GENRES, TAGS, USER_GENRES, USER_TAGS)
                        .addAccessToken(OAuthManager.accessToken?.token ?: "")
                        .lookup()
                },
                { ArtistMapper().mapTo(this) },
                true
            )
        }
    }

}