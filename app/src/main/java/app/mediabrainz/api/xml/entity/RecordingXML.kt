package app.mediabrainz.api.xml.entity

import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root
import java.util.*


@Root(name = "recording")
class RecordingXML() : BaseTagRatingEntityXML() {

    constructor(id: String) : this() {
        this.id = id
    }

    @field:ElementList(name = "isrc-list", required = false)
    var isrcs: List<IsrcXML>? = null

    fun addIsrcs(vararg isrcs: IsrcXML) {
        if (this.isrcs == null) this.isrcs = ArrayList()
        (this.isrcs as ArrayList).addAll(isrcs)
    }

}
