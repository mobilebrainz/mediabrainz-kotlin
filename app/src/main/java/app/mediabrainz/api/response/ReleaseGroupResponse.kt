package app.mediabrainz.api.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class ReleaseGroupResponse(
    @Json(name = "id") val mbid: String,
    @Json(name = "title") val title: String = "",
    @Json(name = "count") val count: Int = 0,
    @Json(name = "score") val score: Int = 0,
    @Json(name = "primary-type") val primaryType: String = "",
    @Json(name = "primary-type-id") val primaryTypeId: String = "",
    @Json(name = "secondary-types") val secondaryTypes: List<String> = ArrayList(),
    @Json(name = "secondary-type-ids") val secondaryTypeIds: List<String> = ArrayList(),
    @Json(name = "disambiguation") val disambiguation: String = "",
    @Json(name = "first-release-date") val firstReleaseDate: String = "", //"yyyy-mm-dd"
    //inc=releases
    @Json(name = "releases") val releases: List<ReleaseResponse> = ArrayList(),
    //inc=artists || inc=artist-credits
    @Json(name = "artist-credit") val artistCredits: List<ArtistCreditResponse> = ArrayList(),
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
) : BaseLookupResponse()

@JsonClass(generateAdapter = true)
data class ReleaseGroupSearchResponse(
    @Json(name = "count") val count: Int = 0,
    @Json(name = "offset") val offset: Int = 0,
    @Json(name = "release-groups") val releaseGroups: List<ReleaseGroupResponse> = ArrayList()
) : BaseSearchResponse()

@JsonClass(generateAdapter = true)
data class ReleaseGroupBrowseResponse(
    @Json(name = "release-group-count") val count: Int = 0,
    @Json(name = "release-group-offset") val offset: Int = 0,
    @Json(name = "release-groups") val releaseGroups: List<ReleaseGroupResponse> = ArrayList()
)

interface ReleaseGroupType

/**
 * https://musicbrainz.org/doc/Release_Group/Type
 */
enum class ReleaseGroupPrimaryType(val type: String) : ReleaseGroupType {
    ALBUM("album"),
    SINGLE("single"),
    EP("ep"),
    BROADCAST("broadcast"),
    OTHER("other"),

    EMPTY(""),
    ANY("(*)"),
    NOTHING("(-*)")
}

/**
 * https://musicbrainz.org/doc/Release_Group/Type
 */
enum class ReleaseGroupSecondaryType(val type: String) : ReleaseGroupType {
    COMPILATION("compilation"),
    SOUNDTRACK("soundtrack"),
    SPOKENWORD("spokenword"),
    INTERVIEW("interview"),
    AUDIOBOOK("audiobook"),
    LIVE("live"),
    REMIX("remix"),
    DJ_MIX("dj-mix"),
    MIXTAPE("mixtape"),
    STREET("street"),

    EMPTY(""),
    ANY("(*)"),
    NOTHING("(-*)")
}