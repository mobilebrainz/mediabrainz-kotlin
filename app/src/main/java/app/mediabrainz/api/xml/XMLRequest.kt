package app.mediabrainz.api.xml

import app.mediabrainz.api.core.Config.WEB_SERVICE
import app.mediabrainz.api.core.WebService
import app.mediabrainz.api.xml.entity.ArtistXML
import app.mediabrainz.api.xml.entity.MetadataXML
import app.mediabrainz.api.xml.entity.UserTagXML
import kotlinx.coroutines.Deferred
import retrofit2.Response


class XMLRequest(val client: String, val accessToken: String) : XMLRequestInterface {

    private fun createXMLRetrofitService() = WebService
        .createXMLRetrofitService(XMLRequestService::class.java, WEB_SERVICE)

    override fun postArtistTags(artistMbid: String, vararg tags: UserTagXML): Deferred<Response<MetadataXML>> {
        val artistXML = ArtistXML(artistMbid)
        artistXML.addUserTags(*tags)
        val metadataXML = MetadataXML()
        metadataXML.addArtists(artistXML)
        return createXMLRetrofitService().postMetadata(XMLPathType.TAG.toString(), metadataXML, client, accessToken)
    }

}