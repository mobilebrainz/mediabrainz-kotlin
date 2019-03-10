package app.mediabrainz.api.response

import app.mediabrainz.api.search.BaseSearchResponse
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * https://musicbrainz.org/doc/Recording
 */
@JsonClass(generateAdapter = true)
data class RecordingResponse(
    @Json(name = "id") val mbid: String,
    @Json(name = "title") val title: String,
    @Json(name = "score") val score: Int = 0,
    @Json(name = "length") val length: Long = 0L,
    @Json(name = "disambiguation") val disambiguation: String = "",
    @Json(name = "video") val video: Boolean = false,
    // TODO: do adapter for ISRCS
    /*
     conflict with return type isrcs field
     search: https://musicbrainz.org/ws/2/recording?fmt=json&query=isrc:USGF19942501
     lookup: https://musicbrainz.org/ws/2/recording/6da76448-982a-4a01-b65b-9a710301c9c9?fmt=json&inc=isrcs
     search return array of objects with fields id = isrc
     lookup ISRCS return array of String isrc
     */
    @Json(name = "isrcs") val isrcs: List<String> = ArrayList(), // from lookup inc=isrcs
    //@Json(name = "isrcs") val isrcs: List<ISRCResponse> = ArrayList(), // from search query=isrc - ERROR!
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
    //inc=artists || inc=artist-credits
    @Json(name = "artist-credit") val artistCredits: List<ArtistCreditResponse> = ArrayList(),
    //inc=releases
    @Json(name = "releases") val releases: List<ReleaseResponse> = ArrayList(),
    //inc=...-rels
    @Json(name = "relations")
    val relations: List<RelationResponse> = ArrayList()
) : BaseLookupResponse

@JsonClass(generateAdapter = true)
data class RecordingSearchResponse(
    @Json(name = "count") val count: Int = 0,
    @Json(name = "offset") val offset: Int = 0,
    @Json(name = "recordings") val recordings: List<RecordingResponse> = ArrayList()
) : BaseSearchResponse

@JsonClass(generateAdapter = true)
data class RecordingBrowseResponse(
    @Json(name = "recording-count") val count: Int = 0,
    @Json(name = "recording-offset") val offset: Int = 0,
    @Json(name = "recordings") val recordings: List<RecordingResponse> = ArrayList()
)