package app.mediabrainz.api.xml.entity

import org.simpleframework.xml.Root


@Root(name = "release-group")
class ReleaseGroupXML() : BaseTagRatingEntityXML() {

    constructor(id: String) : this() {
        this.id = id
    }
}