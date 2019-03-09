package app.mediabrainz.api.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RelationResponse(
    /**
     * https://musicbrainz.org/relationship-attributes
     */
    @Json(name = "attributes") val attributes: List<String> = ArrayList(),
    @Json(name = "attribute-credits") val attributeCredits: Map<String, String> = HashMap(),
    /**
     * attribute-values: Map<String, String> - значения аттрибутов, имена которых заданы в attributes:
     * attributes["time"],  attribute-values{time: "23:45"} (inc=event-rels)
     */
    @Json(name = "attribute-values") val attributeValues: Map<String, String> = HashMap(),
    @Json(name = "begin") val begin: String = "",
    @Json(name = "direction") val direction: String = "",
    @Json(name = "end") val end: String = "",
    @Json(name = "ended") val ended: Boolean = false,
    @Json(name = "source-credit") val sourceCredit: String = "",
    @Json(name = "target-type") val targetType: String = "",
    @Json(name = "type") val type: String = "",
    @Json(name = "type-id") val typeId: String = "",
    @Json(name = "area") val area: AreaResponse? = null,
    @Json(name = "artist") val artist: ArtistResponse? = null,
    @Json(name = "event") val event: EventResponse? = null,
    @Json(name = "instrument") val instrument: InstrumentResponse? = null,
    @Json(name = "label") val label: LabelResponse? = null,
    @Json(name = "place") val place: PlaceResponse? = null,
    @Json(name = "recording") val recording: RecordingResponse? = null,
    @Json(name = "release") val release: ReleaseResponse? = null,
    @Json(name = "release_group") val releaseGroup: ReleaseGroupResponse? = null,
    @Json(name = "url") val url: UrlResponse? = null,
    @Json(name = "work") val work: WorkResponse? = null,
    @Json(name = "series") val series: SeriesResponse? = null
)

/**
 * https://musicbrainz.org/relationships/artist-artist
 */
enum class ArtistArtistRelationType(val type: String) {
    MEMBER_OF_BAND("member of band"),
    SUBGROUP("subgroup"),
    CONDUCTOR_POSITION("conductor position"),
    FOUNDER("founder"),
    SUPPORTING_MUSICIAN("supporting musician"),
    VOCAL_SUPPORTING_MUSICIAN("vocal supporting musician"),
    INSTRUMENTAL_SUPPORTING_MUSICIAN("instrumental supporting musician"),
    TRIBUTE("tribute"),
    VOICE_ACTOR("voice actor"),
    COLLABORATION("collaboration"),
    IS_PERSON("is person"),
    TEACHER("teacher"),
    COMPOSER_IN_RESIDENCE("composer-in-residence");
}