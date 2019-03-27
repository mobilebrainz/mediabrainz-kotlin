package app.mediabrainz.api.response

import app.mediabrainz.api.lookupbrowse.BrowseResponseInterface
import app.mediabrainz.api.lookupbrowse.LookupResponseInterface
import app.mediabrainz.api.search.SearchResponseInterface
import com.squareup.moshi.Json

/**
 * https://musicbrainz.org/doc/Recording
 */
data class RecordingResponse(
    @field:Json(name = "id") val mbid: String,
    @field:Json(name = "title") val title: String,
    @field:Json(name = "score") val score: Int?,
    @field:Json(name = "length") val length: Long?,
    @field:Json(name = "disambiguation") val disambiguation: String?,
    @field:Json(name = "video") val video: Boolean?,
    // TODO: do adapter for ISRCS
    /*
     conflict with return type isrcs field
     search: https://musicbrainz.org/ws/2/recording?fmt=json&query=isrc:USGF19942501
     lookup: https://musicbrainz.org/ws/2/recording/6da76448-982a-4a01-b65b-9a710301c9c9?fmt=json&inc=isrcs
     search return array of objects with fields id = isrc
     lookup ISRCS return array of String isrc
     */
    @field:Json(name = "isrcs") val isrcs: List<String>?, // from lookup inc=isrcs
    //@Json(name = "isrcs") val isrcs: List<ISRCResponse> = ArrayList(), // from search query=isrc - ERROR!
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
     * inc=artists || inc=artist-credits
     */
    @field:Json(name = "artist-credit") val artistCredits: List<ArtistCreditResponse>?,
    /**
     * inc=releases
     */
    @field:Json(name = "releases") val releases: List<ReleaseResponse>?,
    /**
     * inc=...-rels
     */
    @field:Json(name = "relations") val relations: List<RelationResponse>?
) : LookupResponseInterface

class RecordingSearchResponse : BaseItemsResponse<RecordingResponse>(), SearchResponseInterface {
    @field:Json(name = "recordings")
    override var items: List<RecordingResponse> = ArrayList()
}

class RecordingBrowseResponse : BaseItemsResponse<RecordingResponse>(), BrowseResponseInterface {
    @field:Json(name = "recording-count")
    override var count: Int = 0

    @field:Json(name = "recording-offset")
    override var offset: Int = 0

    @field:Json(name = "recordings")
    override var items: List<RecordingResponse> = ArrayList()
}