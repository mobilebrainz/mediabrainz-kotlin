package app.mediabrainz.api.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * https://musicbrainz.org/doc/Release
 */
@JsonClass(generateAdapter = true)
data class ReleaseResponse(
    @Json(name = "id") val mbid: String,
    @Json(name = "title") val title: String,
    @Json(name = "cover-art-archive") val coverArt: CoverArtResponse? = null,
    @Json(name = "score") val score: Int = 0,
    @Json(name = "count") val count: Int = 0,
    @Json(name = "status") val status: String = "",
    @Json(name = "status-id") val statusId: String = "",
    @Json(name = "packaging") val packaging: String = "",
    @Json(name = "packaging-id") val packagingId: String = "",
    @Json(name = "date") val date: String = "", //"yyyy-mm-dd"
    @Json(name = "country") val country: String = "",
    @Json(name = "barcode") val barcode: String = "",
    @Json(name = "disambiguation") val disambiguation: String = "",
    @Json(name = "asin") val asin: String = "",
    @Json(name = "track-count") val trackCount: Int = 0,
    @Json(name = "text-representation") val textRepresentation: TextRepresentationResponse? = null,
    @Json(name = "release-events") val releaseEvents: List<ReleaseEventResponse> = ArrayList(),
    @Json(name = "quality") val quality: String = "",
    //inc=aliases
    @Json(name = "aliases") val aliases: List<AliasResponse> = ArrayList(),
    //inc=annotation
    @Json(name = "annotation") val annotation: String = "",
    //inc=tags
    @Json(name = "tags") val tags: List<TagResponse> = ArrayList(),
    //inc=user-tags
    @Json(name = "user-tags") val userTags: List<TagResponse> = ArrayList(),
    //inc=genres
    @Json(name = "genres") val genres: List<TagResponse> = ArrayList(),
    //inc=user-genres
    @Json(name = "user-genres") val userGenres: List<TagResponse> = ArrayList(),
    //inc=artists || inc=artist-credits
    @Json(name = "artist-credit") val artistCredits: List<ArtistCreditResponse> = ArrayList(),
    //inc=collections
    @Json(name = "collections") val collections: List<CollectionResponse> = ArrayList(),
    //inc=labels
    @Json(name = "label-info") val labelInfo: List<LabelInfoResponse> = ArrayList(),
    //inc=release-groups
    @Json(name = "release-group") val releaseGroup: ReleaseGroupResponse? = null,
    //inc=media, inc=recordings equal inc=media with track infos
    @Json(name = "media") val media: List<Media> = ArrayList(),
    //inc=...-rels
    @Json(name = "relations") val relations: List<RelationResponse> = ArrayList()
) : BaseLookupResponse()

@JsonClass(generateAdapter = true)
data class ReleaseSearchResponse(
    @Json(name = "count") val count: Int = 0,
    @Json(name = "offset") val offset: Int = 0,
    @Json(name = "releases") val releases: List<ReleaseResponse> = ArrayList()
) : BaseSearchResponse()

@JsonClass(generateAdapter = true)
data class ReleaseBrowseResponse(
    @Json(name = "release-count") val count: Int = 0,
    @Json(name = "release-offset") val offset: Int = 0,
    @Json(name = "releases") val releases: List<ReleaseResponse> = ArrayList()
)

@JsonClass(generateAdapter = true)
data class TextRepresentationResponse(
    @Json(name = "language") val language: String = "",
    @Json(name = "script") val script: String = ""
)

@JsonClass(generateAdapter = true)
data class ReleaseEventResponse(
    @Json(name = "date") val date: String = "", //"yyyy-mm-dd"
    @Json(name = "area") val area: AreaResponse? = null
)

enum class Status(val status: String) {
    OFFICIAL("official"),
    PROMOTIONAL("promotional"),
    BOOTLEG("bootleg"),
    PSEUDO_RELEASE("pseudo-release")
}

// TODO: Check all types. (is "normal" type?)
enum class DataQuality(val quality: String) {
    HIGH_QUALITY("High quality"),
    DEFAULT_QUALITY("Default quality"),
    LOW_QUALITY("Low quality")
}

