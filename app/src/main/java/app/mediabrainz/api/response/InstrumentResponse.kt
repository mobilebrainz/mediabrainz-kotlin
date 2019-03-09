package app.mediabrainz.api.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class InstrumentResponse(
    @Json(name = "id") val id: String,
    @Json(name = "name") val name: String,
    @Json(name = "score") val score: Int = 0,
    @Json(name = "type") val type: String = "",
    @Json(name = "type-id") val typeId: String = "",
    @Json(name = "description") val description: String = "",
    @Json(name = "disambiguation") val disambiguation: String = "",
    //inc=aliases
    @Json(name = "aliases") val aliases: List<AliasResponse> = ArrayList(),
    //inc=annotation
    @Json(name = "annotation") val annotation: String = "",
    //inc=tags
    @Json(name = "tags") val tags : List<TagResponse> = ArrayList(),
    //inc=user-tags
    @Json(name = "user-tags") val userTags : List<TagResponse> = ArrayList(),
    //inc=...-rels
    @Json(name = "relations") val relations: List<RelationResponse> = ArrayList()
) : BaseLookupResponse()

@JsonClass(generateAdapter = true)
data class InstrumentSearchResponse(
    @Json(name = "count") val count: Int = 0,
    @Json(name = "offset") val offset: Int = 0,
    @Json(name = "instruments") val instruments: List<InstrumentResponse> = ArrayList()
) : BaseSearchResponse()

enum class InstrumentType(val type: String) {
    WIND_INSTRUMENT("Wind instrument"),
    STRING_INSTRUMENT("String instrument"),
    PERCUSSION_INSTRUMENT("Percussion instrument"),
    ELECTRONIC_INSTRUMENT("Electronic instrument"),
    OTHER_INSTRUMENT("Other instrument"),
    UNCLASSIFIED_INSTRUMENT("Unclassified instrument")
}