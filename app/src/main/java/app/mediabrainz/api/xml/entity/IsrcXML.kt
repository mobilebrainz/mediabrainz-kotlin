package app.mediabrainz.api.xml.entity

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Root


@Root(name = "isrc")
data class IsrcXML(
    @field:Attribute(name = "id") val id: String
)