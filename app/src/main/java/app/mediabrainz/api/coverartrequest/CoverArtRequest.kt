package app.mediabrainz.api.coverartrequest

import app.mediabrainz.api.core.WebService
import app.mediabrainz.api.retrofit.CoverArtRequestService
import app.mediabrainz.api.retrofit.CoverArtRequestService.Companion.COVERART_WEB_SERVICE
import app.mediabrainz.api.retrofit.CoverArtRequestService.Companion.RELEASE_GROUP_PATH
import app.mediabrainz.api.retrofit.CoverArtRequestService.Companion.RELEASE_PATH


class CoverArtRequest(val mbid: String) : CoverArtRequestInterface {

    private fun createJsonRetrofitService() = WebService
        .createJsonRetrofitService(CoverArtRequestService::class.java, COVERART_WEB_SERVICE)

    override fun getReleaseCoverArt() =
        createJsonRetrofitService().getCoverArts(RELEASE_PATH, mbid)

    override fun getReleaseGroupCoverArt() =
        createJsonRetrofitService().getCoverArts(RELEASE_GROUP_PATH, mbid)
}