package app.mediabrainz.api

import app.mediabrainz.api.xml.XMLRequest
import app.mediabrainz.api.xml.entity.UserTagXML


object XMLRequestProvider {

    fun postArtistTags(client: String, accessToken: String, artistMbid: String, vararg tags: UserTagXML) =
        XMLRequest(client, accessToken).postArtistTags(artistMbid, *tags)

}