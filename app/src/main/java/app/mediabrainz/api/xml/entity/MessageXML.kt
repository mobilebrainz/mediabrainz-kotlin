package app.mediabrainz.api.xml.entity

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root


@Root(name = "message")
class MessageXML {

    @field:Element(name = "text")
    var text: String = ""

    companion object {
        const val MessageXML_OK = "OK"
    }
}