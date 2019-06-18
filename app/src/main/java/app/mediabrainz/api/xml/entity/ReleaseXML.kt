package app.mediabrainz.api.xml.entity

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root


@Root(name = "release")
class ReleaseXML(
    @field:Attribute(name="id") val id: String,
    @field:Element(name="barcode") val barcode: String
)

