package app.mediabrainz.api.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class CollectionResponse(
    @Json(name = "id") val id : String,
    @Json(name = "name") val name : String,
    @Json(name = "editor") val editor : String,
    @Json(name = "type") val type : String = "",
    @Json(name = "type-id") val typeId : String = "",




    //inc=...-rels
    //@Json(name = "relations") List<RelationResponse> relations = ArrayList()
)