package app.mediabrainz.domain.mapper

import app.mediabrainz.api.xml.entity.MessageXML.Companion.MessageXML_OK
import app.mediabrainz.api.xml.entity.MetadataXML


class MetadataMapper {

    fun postOk(response: MetadataXML): Boolean =
        response.message != null && response.message!!.text == MessageXML_OK
}