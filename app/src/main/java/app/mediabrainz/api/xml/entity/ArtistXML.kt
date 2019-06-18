package app.mediabrainz.api.xml.entity

import org.simpleframework.xml.Root


@Root(name = "artist")
class ArtistXML() : BaseTagRatingEntityXML() {

    constructor(id: String) : this() {
        this.id = id
    }
}

