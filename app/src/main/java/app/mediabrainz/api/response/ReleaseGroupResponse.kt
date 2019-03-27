package app.mediabrainz.api.response

import app.mediabrainz.api.lookupbrowse.BrowseResponseInterface
import app.mediabrainz.api.lookupbrowse.LookupResponseInterface
import app.mediabrainz.api.search.SearchResponseInterface
import com.squareup.moshi.Json


data class ReleaseGroupResponse(
    @field:Json(name = "id") val mbid: String,
    @field:Json(name = "title") val title: String,
    @field:Json(name = "count") val count: Int?,
    @field:Json(name = "score") val score: Int?,
    @field:Json(name = "primary-type") val primaryType: String?,
    @field:Json(name = "primary-type-id") val primaryTypeId: String?,
    @field:Json(name = "secondary-types") val secondaryTypes: List<String>?,
    @field:Json(name = "secondary-type-ids") val secondaryTypeIds: List<String>?,
    @field:Json(name = "disambiguation") val disambiguation: String?,
    @field:Json(name = "first-release-date") val firstReleaseDate: String?, //"yyyy-mm-dd"
    /**
     * inc=releases
     */
    @field:Json(name = "releases") val releases: List<ReleaseResponse>?,
    /**
     * inc=artists || inc=artist-credits
     */
    @field:Json(name = "artist-credit") val artistCredits: List<ArtistCreditResponse>?,
    /**
     * inc=aliases
     */
    @field:Json(name = "aliases") val aliases: List<AliasResponse>?,
    /**
     * inc=annotation
     */
    @field:Json(name = "annotation") val annotation: String?,
    /**
     * inc=ratings
     */
    @field:Json(name = "rating") val rating: RatingResponse?,
    /**
     * inc=user-ratings
     */
    @field:Json(name = "user-rating") val userRating: RatingResponse?,
    /**
     * inc=tags
     */
    @field:Json(name = "tags") val tags: List<TagResponse>?,
    /**
     * inc=user-tags
     */
    @field:Json(name = "user-tags") val userTags: List<TagResponse>?,
    /**
     * inc=genres
     */
    @field:Json(name = "genres") val genres: List<TagResponse>?,
    /**
     * inc=user-genres
     */
    @field:Json(name = "user-genres") val userGenres: List<TagResponse>?,
    /**
     * inc=...-rels
     */
    @field:Json(name = "relations") val relations: List<RelationResponse>?
) : LookupResponseInterface

class ReleaseGroupSearchResponse : BaseItemsResponse<ReleaseGroupResponse>(), SearchResponseInterface {
    @field:Json(name = "release-groups")
    override var items: List<ReleaseGroupResponse> = ArrayList()
}

class ReleaseGroupBrowseResponse : BaseItemsResponse<ReleaseGroupResponse>(), BrowseResponseInterface {
    @field:Json(name = "release-group-count")
    override var count: Int = 0

    @field:Json(name = "release-group-offset")
    override var offset: Int = 0

    @field:Json(name = "release-groups")
    override var items: List<ReleaseGroupResponse> = ArrayList()
}

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
    NOTHING("(-*)");

    override fun toString() = type
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
    NOTHING("(-*)");

    override fun toString() = type
}