package app.mediabrainz.api.response

import app.mediabrainz.api.lookup.LookupResponseInterface
import com.squareup.moshi.Json

/**
 * https://musicbrainz.org/doc/Release
 */
data class ReleaseResponse(
    @field:Json(name = "id") val mbid: String,
    @field:Json(name = "title") val title: String,
    @field:Json(name = "cover-art-archive") val coverArt: CoverArtResponse?,
    @field:Json(name = "score") val score: Int?,
    @field:Json(name = "count") val count: Int?,
    @field:Json(name = "status") val status: String?,
    @field:Json(name = "status-id") val statusId: String?,
    @field:Json(name = "packaging") val packaging: String?,
    @field:Json(name = "packaging-id") val packagingId: String?,
    @field:Json(name = "date") val date: String?, //"yyyy-mm-dd"
    @field:Json(name = "country") val country: String?,
    @field:Json(name = "barcode") val barcode: String?,
    @field:Json(name = "disambiguation") val disambiguation: String?,
    @field:Json(name = "asin") val asin: String?,
    @field:Json(name = "track-count") val trackCount: Int?,
    @field:Json(name = "text-representation") val textRepresentation: TextRepresentationResponse?,
    @field:Json(name = "release-events") val releaseEvents: List<ReleaseEventResponse>?,
    @field:Json(name = "quality") val quality: String?,
    //inc=aliases
    @field:Json(name = "aliases") val aliases: List<AliasResponse>?,
    //inc=annotation
    @field:Json(name = "annotation") val annotation: String?,
    //inc=tags
    @field:Json(name = "tags") val tags: List<TagResponse>?,
    //inc=user-tags
    @field:Json(name = "user-tags") val userTags: List<TagResponse>?,
    //inc=genres
    @field:Json(name = "genres") val genres: List<TagResponse>?,
    //inc=user-genres
    @field:Json(name = "user-genres") val userGenres: List<TagResponse>?,
    //inc=artists || inc=artist-credits
    @field:Json(name = "artist-credit") val artistCredits: List<ArtistCreditResponse>?,
    //inc=collections
    @field:Json(name = "collections") val collections: List<CollectionResponse>?,
    //inc=labels
    @field:Json(name = "label-info") val labelInfo: List<LabelInfoResponse>?,
    //inc=release-groups
    @field:Json(name = "release-group") val releaseGroup: ReleaseGroupResponse?,
    //inc=media, inc=recordings equal inc=media with track infos
    @field:Json(name = "media") val media: List<Media>?,
    //inc=...-rels
    @field:Json(name = "relations") val relations: List<RelationResponse>?
) : LookupResponseInterface

class ReleaseSearchResponse : BaseSearchResponse<ReleaseResponse>() {
    @field:Json(name = "releases")
    val releases: List<ReleaseResponse> = ArrayList()

    override fun getItems() = releases
}

data class ReleaseBrowseResponse(
    @field:Json(name = "release-count") val count: Int,
    @field:Json(name = "release-offset") val offset: Int,
    @field:Json(name = "releases") val releases: List<ReleaseResponse>
)

data class TextRepresentationResponse(
    @field:Json(name = "language") val language: String?,
    @field:Json(name = "script") val script: String?
)

data class ReleaseEventResponse(
    @field:Json(name = "date") val date: String?, //"yyyy-mm-dd"
    @field:Json(name = "area") val area: AreaResponse?
)

enum class Status(val status: String) {
    OFFICIAL("official"),
    PROMOTIONAL("promotional"),
    BOOTLEG("bootleg"),
    PSEUDO_RELEASE("pseudo-release");

    override fun toString() = status
}

// TODO: Check all types. (is "normal" type?)
enum class DataQuality(val quality: String) {
    HIGH_QUALITY("High quality"),
    DEFAULT_QUALITY("Default quality"),
    LOW_QUALITY("Low quality");

    override fun toString() = quality
}

