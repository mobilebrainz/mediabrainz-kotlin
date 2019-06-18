package app.mediabrainz.api.xml.entity

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import java.util.*


abstract class BaseTagRatingEntityXML {

    @field:Attribute(name = "id")
    open var id: String? = null

    @field:Element(name = "user-rating", required = false)
    open var userRating: Int? = null

    @field:ElementList(name = "user-tag-list", required = false)
    open var userTags: List<UserTagXML>? = null

    fun addUserTags(vararg userTags: UserTagXML) {
        if (this.userTags == null) this.userTags = ArrayList()
        (this.userTags as ArrayList).addAll(userTags)
    }
}
