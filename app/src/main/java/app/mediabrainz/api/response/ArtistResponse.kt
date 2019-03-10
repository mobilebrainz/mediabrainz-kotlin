package app.mediabrainz.api.response

import app.mediabrainz.api.lookup.LookupResponseInterface
import app.mediabrainz.api.search.SearchResponseInterface
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * https://musicbrainz.org/doc/Artist
 */
@JsonClass(generateAdapter = true)
data class ArtistResponse(
    @Json(name = "id") val mbid: String,
    @Json(name = "name") val name: String,
    @Json(name = "sort-name") val sortName: String = "",
    @Json(name = "disambiguation") val disambiguation: String = "",
    @Json(name = "type") val type: String = "",
    @Json(name = "type-id") val typeId: String = "",
    @Json(name = "country") val country: String = "",
    @Json(name = "score") val score: Int = 0,
    @Json(name = "gender") val gender: String = "",
    @Json(name = "area") val area: AreaResponse? = null,
    @Json(name = "begin-area") val beginArea: AreaResponse? = null,
    @Json(name = "end-area") val endArea: AreaResponse? = null,
    @Json(name = "life-span") val lifeSpan: LifeSpanResponse? = null,
    @Json(name = "isnis") val isnis: List<String> = ArrayList(),
    @Json(name = "ipis") val ipis: List<String> = ArrayList(),
    //inc=works
    @Json(name = "works") val works: List<WorkResponse> = ArrayList(),
    //inc=recordings
    @Json(name = "recordings") val recordings: List<RecordingResponse> = ArrayList(),
    //inc=releases
    @Json(name = "releases") val releases: List<ReleaseResponse> = ArrayList(),
    //inc=release-groups
    @Json(name = "release-groups") val releaseGroups: List<ReleaseGroupResponse> = ArrayList(),
    //inc=aliases
    @Json(name = "aliases") val aliases: List<AliasResponse> = ArrayList(),
    //inc=annotation
    @Json(name = "annotation") val annotation: String = "",
    //inc=ratings
    @Json(name = "rating") val rating: RatingResponse = RatingResponse(),
    //inc=user-ratings
    @Json(name = "user-rating") val userRating: RatingResponse = RatingResponse(),
    //inc=tags
    @Json(name = "tags") val tags: List<TagResponse> = ArrayList(),
    //inc=user-tags
    @Json(name = "user-tags") val userTags: List<TagResponse> = ArrayList(),
    //inc=genres
    @Json(name = "genres") val genres: List<TagResponse> = ArrayList(),
    //inc=user-genres
    @Json(name = "user-genres") val userGenres: List<TagResponse> = ArrayList(),
    //inc=...-rels
    @Json(name = "relations") val relations: List<RelationResponse> = ArrayList()
) : LookupResponseInterface

@JsonClass(generateAdapter = true)
data class ArtistSearchResponse(
    @Json(name = "count") val count: Int = 0,
    @Json(name = "offset") val offset: Int = 0,
    @Json(name = "artists") val artists: List<ArtistResponse> = ArrayList()
) : SearchResponseInterface

@JsonClass(generateAdapter = true)
data class ArtistBrowseResponse(
    @Json(name = "artist-count") val count: Int = 0,
    @Json(name = "artist-offset") val offset: Int = 0,
    @Json(name = "artists") val artists: List<ArtistResponse> = ArrayList()
)

@JsonClass(generateAdapter = true)
data class ArtistCreditResponse(
    @Json(name = "artist") val artist: ArtistResponse,
    @Json(name = "name") val name: String,
    @Json(name = "joinphrase") val joinphrase: String = ""
)

enum class ArtistType(val type: String) {
    PERSON("Person"),
    GROUP("Group"),
    ORCHESTRA("Orchestra"),
    CHOIR("Choir"),
    CHARACTER("Character"),
    OTHER("Other");

    override fun toString() = type
}