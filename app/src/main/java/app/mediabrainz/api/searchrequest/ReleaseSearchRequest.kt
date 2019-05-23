package app.mediabrainz.api.searchrequest

import app.mediabrainz.api.response.MediaFormatTypeResponse
import app.mediabrainz.api.response.ReleaseSearchResponse
import app.mediabrainz.api.response.ReleaseStatusResponse
import app.mediabrainz.api.search.BaseSearchRequest
import app.mediabrainz.api.search.LuceneOperator
import app.mediabrainz.api.search.SearchFieldInterface
import app.mediabrainz.api.searchrequest.ReleaseSearchField.FORMAT
import app.mediabrainz.api.searchrequest.ReleaseSearchField.STATUS


/**
 * unconditional search: Release search terms with no fields search the RELEASE field only.
 *  ReleaseSearchService().search("Stair")
 *  ReleaseSearchService().search("Stair", 2, 10)
 */
class ReleaseSearchRequest :
    BaseSearchRequest<ReleaseSearchResponse, ReleaseSearchField>() {

    override fun search() = createJsonRetrofitService().searchRelease(buildParams())

    fun addStatus(status: ReleaseStatusResponse): ReleaseSearchRequest {
        add(STATUS, status.status)
        return this
    }

    fun addFormat(format: MediaFormatTypeResponse): ReleaseSearchRequest {
        add(FORMAT, format.format)
        return this
    }

    override fun add(operator: LuceneOperator): ReleaseSearchRequest {
        super.add(operator)
        return this
    }
}

enum class ReleaseSearchField(val field: String) : SearchFieldInterface {
    /**
     * artist id
     */
    ARID("arid"),

    /**
     * complete artist name(s) as it appears on the release
     */
    ARTIST("artist"),

    /**
     * an artist on the release, each artist added as a separate field
     */
    ARTIST_NAME("artistname"),

    /**
     * the Amazon ASIN for this release
     */
    ASIN("asin"),

    /**
     * The barcode of this release
     */
    BARCODE("barcode"),

    /**
     * The catalog number for this release, can have multiples when major using an imprint
     */
    CATNO("catno"),

    /**
     * Disambiguation comment
     */
    COMMENT("comment"),

    /**
     * The two letter country code for the release country
     */
    COUNTRY("country"),

    /**
     * name credit on the release, each artist added as a separate field
     */
    CREDIT_NAME("creditname"),

    /**
     * The release date (format: YYYY-MM-DD)
     */
    DATE("date"),

    /**
     * total number of cd ids over all mediums for the release
     */
    DISCIDS("discids"),

    /**
     * number of cd ids for the release on a medium in the release
     */
    DISCIDS_MEDIUM("discidsmedium"),

    /**
     * release format
     */
    FORMAT("format"),

    /**
     * The label id for this release, a release can have multiples when major using an imprint
     */
    LAID("laid"),

    /**
     * The name of the label for this release, can have multiples when major using an imprint
     */
    LABEL("label"),

    /**
     * The language for this release. Use the three character ISO 639 codes to search for a specific language. (e.g. lang:eng)
     */
    LANG("lang"),

    /**
     * number of mediums in the release
     */
    MEDIUMS("mediums"),

    /**
     * primary type of the release group (album, single, ep, other)
     */
    PRIMARY_TYPE("primarytype"),

    /**
     * The release contains recordings with these puids
     */
    PUID("puid"),

    /**
     * The quality of the release (low, normal, high)
     */
    QUALITY("quality"),

    /**
     * release id
     */
    REID("reid"),

    /**
     * release name
     */
    RELEASE("release"),

    /**
     * name of the release with any accent characters retained
     */
    RELEASE_ACCENT("releaseaccent"),

    /**
     * release group id
     */
    RGID("rgid"),

    /**
     * The 4 character script code (e.g. latn) used for this release
     */
    SCRIPT("script"),

    /**
     * secondary type of the release group (audiobook, compilation, interview, live, remix, soundtrack, spokenword)
     */
    SECONDARY_TYPE("secondarytype"),

    /**
     * release status (e.g official)
     */
    STATUS("status"),

    /**
     * a tag that appears on the release
     */
    TAG("tag"),

    /**
     * total number of tracks over all mediums on the release
     */
    TRACKS("tracks"),

    /**
     * number of tracks on a medium in the release
     */
    TRACKS_MEDIUM("tracksmedium"),

    /**
     * type of the release group, old type mapping for when we did not have separate primary and secondary types
     */
    TYPE("type");

    override fun toString() = field
}