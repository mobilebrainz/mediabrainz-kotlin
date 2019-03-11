package app.mediabrainz.api.response

import app.mediabrainz.api.lookup.LookupResponseInterface
import app.mediabrainz.api.search.SearchResponseInterface
import com.squareup.moshi.Json


data class InstrumentResponse(
    @field:Json(name = "id") val id: String,
    @field:Json(name = "name") val name: String,
    @field:Json(name = "score") val score: Int?,
    @field:Json(name = "type") val type: String?,
    @field:Json(name = "type-id") val typeId: String?,
    @field:Json(name = "description") val description: String?,
    @field:Json(name = "disambiguation") val disambiguation: String?,
    //inc=aliases
    @field:Json(name = "aliases") val aliases: List<AliasResponse>?,
    //inc=annotation
    @field:Json(name = "annotation") val annotation: String?,
    //inc=tags
    @field:Json(name = "tags") val tags: List<TagResponse>?,
    //inc=user-tags
    @field:Json(name = "user-tags") val userTags: List<TagResponse>?,
    //inc=...-rels
    @field:Json(name = "relations") val relations: List<RelationResponse>?
) : LookupResponseInterface

data class InstrumentSearchResponse(
    @field:Json(name = "count") val count: Int,
    @field:Json(name = "offset") val offset: Int,
    @field:Json(name = "instruments") val instruments: List<InstrumentResponse>
) : SearchResponseInterface

enum class InstrumentType(val type: String) {
    WIND_INSTRUMENT("Wind instrument"),
    STRING_INSTRUMENT("String instrument"),
    PERCUSSION_INSTRUMENT("Percussion instrument"),
    ELECTRONIC_INSTRUMENT("Electronic instrument"),
    OTHER_INSTRUMENT("Other instrument"),
    UNCLASSIFIED_INSTRUMENT("Unclassified instrument");

    override fun toString() = type
}