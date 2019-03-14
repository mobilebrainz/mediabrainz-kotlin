package app.mediabrainz.api.searchrequest

import app.mediabrainz.api.core.Config
import app.mediabrainz.api.core.WebService
import app.mediabrainz.api.response.RecordingSearchResponse
import app.mediabrainz.api.retrofit.SearchRequestService
import app.mediabrainz.api.search.BaseSearchRequest
import app.mediabrainz.api.search.SearchFieldInterface


/**
 * unconditional search: Recording search terms with no fields search the RECORDING field only
 *   RecordingSearchService().search("Stair")
 *   RecordingSearchService().search("Stair", 2, 10)
 */
class RecordingSearchRequest :
    BaseSearchRequest<RecordingSearchResponse, RecordingSearchField>() {

    override fun search() = WebService
        .createJsonRetrofitService(SearchRequestService::class.java, Config.WEB_SERVICE)
        .searchRecording(buildParams())
}

enum class RecordingSearchField(val field: String) : SearchFieldInterface {
    /**
     * artist id
     */
    ARID("arid"),

    /**
     * artist name is name(s) as it appears on the recording
     */
    ARTIST("artist"),

    /**
     * an artist on the recording, each artist added as a separate field
     */
    ARTIST_NAME("artistname"),

    /**
     * name credit on the recording, each artist added as a separate field
     */
    CREDIT_NAME("creditname"),

    /**
     * recording disambiguation comment
     */
    COMMENT("comment"),

    /**
     * recording release country
     */
    COUNTRY("country"),

    /**
     * recording release date
     */
    DATE("date"),

    /**
     * duration of track in milliseconds
     */
    DUR("dur"),

    /**
     * recording release format
     */
    FORMAT("format"),

    /**
     * ISRC of recording
     */
    ISRC("isrc"),

    /**
     * free text track number
     */
    NUMBER("number"),

    /**
     * the medium that the recording should be found on, first medium is position 1
     */
    POSITION("position"),

    /**
     * primary type of the release group (album, single, ep, other)
     */
    PRIMARY_TYPE("primarytype"),

    /**
     * quantized duration (duration / 2000)
     */
    QDUR("qdur"),

    /**
     * name of recording or a track associated with the recording
     */
    RECORDING("recording"),

    /**
     * name of the recording with any accent characters retained
     */
    RECORDING_ACCENT("recordingaccent"),

    /**
     * release id
     */
    REID("reid"),

    /**
     * release name
     */
    RELEASE("release"),

    /**
     * release group id
     */
    RGID("rgid"),

    /**
     * recording id
     */
    RID("rid"),

    /**
     * secondary type of the release group (audiobook, compilation, interview, live, remix soundtrack, spokenword)
     */
    SECONDARY_TYPE("secondarytype"),

    /**
     * Release status (official, promotion, Bootleg, Pseudo-Release)
     */
    STATUS("status"),

    /**
     * track id
     */
    TID("tid"),

    /**
     * track number on medium
     */
    TNUM("tnum"),

    /**
     * number of tracks in the medium on release
     */
    TRACKS("tracks"),

    /**
     * number of tracks on release as a whole
     */
    TRACKS_RELEASE("tracksrelease"),

    /**
     * folksonomy tag
     */
    TAG("tag"),

    /**
     * type of the release group, old type mapping for when we did not have separate primary and secondary types or use standalone for standalone recordings
     */
    TYPE("type"),

    /**
     * true to only show video tracks
     */
    VIDEO("video");

    override fun toString() = field

}