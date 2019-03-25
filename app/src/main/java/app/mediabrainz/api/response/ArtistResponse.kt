package app.mediabrainz.api.response

import app.mediabrainz.api.lookupbrowse.BrowseResponseInterface
import app.mediabrainz.api.lookupbrowse.LookupResponseInterface
import com.squareup.moshi.Json

/**
 * https://musicbrainz.org/doc/Artist
 */
data class ArtistResponse(
    @field:Json(name = "id") val mbid: String,
    @field:Json(name = "name") val name: String,
    @field:Json(name = "sort-name") val sortName: String?,
    @field:Json(name = "disambiguation") val disambiguation: String?,
    @field:Json(name = "type") val type: String?,
    @field:Json(name = "type-id") val typeId: String?,
    @field:Json(name = "country") val country: String?,
    @field:Json(name = "score") val score: Int?,
    @field:Json(name = "gender") val gender: String?,
    @field:Json(name = "area") val area: AreaResponse?,
    @field:Json(name = "begin-area") val beginArea: AreaResponse?,
    @field:Json(name = "end-area") val endArea: AreaResponse?,
    @field:Json(name = "life-span") val lifeSpan: LifeSpanResponse?,
    @field:Json(name = "isnis") val isnis: List<String>?,
    @field:Json(name = "ipis") val ipis: List<String>?,
    /**
     * inc=works
     */
    @field:Json(name = "works") val works: List<WorkResponse>?,
    /**
     * inc=recordings
     */
    @field:Json(name = "recordings") val recordings: List<RecordingResponse>?,
    /**
     * inc=releases
     */
    @field:Json(name = "releases") val releases: List<ReleaseResponse>?,
    /**
     * inc=release-groups
     */
    @field:Json(name = "release-groups") val releaseGroups: List<ReleaseGroupResponse>?,
    /**
     * inc=aliases
     */
    @field:Json(name = "aliases") val aliases: List<AliasResponse>?,
    /**
     * inc=annotation
     */
    @field:Json(name = "annotation") val annotation: String?,
    /**
     * nc=ratings
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

class ArtistSearchResponse : BaseSearchResponse<ArtistResponse>() {
    @field:Json(name = "artists")
    var artists: List<ArtistResponse> = ArrayList()

    override fun getItems() = artists
}

data class ArtistBrowseResponse(
    @field:Json(name = "artist-count") val count: Int,
    @field:Json(name = "artist-offset") val offset: Int,
    @field:Json(name = "artists") val artists: List<ArtistResponse>
) : BrowseResponseInterface

data class ArtistCreditResponse(
    @field:Json(name = "artist") val artist: ArtistResponse,
    @field:Json(name = "name") val name: String,
    @field:Json(name = "joinphrase") val joinphrase: String?
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