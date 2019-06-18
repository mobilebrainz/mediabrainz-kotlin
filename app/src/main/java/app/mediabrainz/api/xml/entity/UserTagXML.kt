package app.mediabrainz.api.xml.entity

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root


@Root(name = "user-tag")
data class UserTagXML(
    @field:Element(name = "name") val name: String,
    @field:Attribute(name = "vote", required = false) val vote: String
)

enum class TagVoteType(val type: String) {
    UPVOTE("upvote"),
    DOWNVOTE("downvote"),
    WITHDRAW("withdraw");

    override fun toString() = type
}