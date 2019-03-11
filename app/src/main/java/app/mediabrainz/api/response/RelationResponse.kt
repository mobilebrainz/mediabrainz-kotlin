package app.mediabrainz.api.response

import com.squareup.moshi.Json


data class RelationResponse(
    /**
     * https://musicbrainz.org/relationship-attributes
     */
    @field:Json(name = "attributes") val attributes: List<String>?,
    @field:Json(name = "attribute-credits") val attributeCredits: Map<String, String>?,
    /**
     * attribute-values: Map<String, String> - значения аттрибутов, имена которых заданы в attributes:
     * attributes["time"],  attribute-values{time: "23:45"} (inc=event-rels)
     */
    @field:Json(name = "attribute-values") val attributeValues: Map<String, String>?,
    @field:Json(name = "begin") val begin: String?,
    @field:Json(name = "direction") val direction: String?,
    @field:Json(name = "end") val end: String?,
    @field:Json(name = "ended") val ended: Boolean?,
    @field:Json(name = "source-credit") val sourceCredit: String?,
    @field:Json(name = "target-type") val targetType: String?,
    @field:Json(name = "type") val type: String?,
    @field:Json(name = "type-id") val typeId: String?,
    @field:Json(name = "area") val area: AreaResponse?,
    @field:Json(name = "artist") val artist: ArtistResponse?,
    @field:Json(name = "event") val event: EventResponse?,
    @field:Json(name = "instrument") val instrument: InstrumentResponse?,
    @field:Json(name = "label") val label: LabelResponse?,
    @field:Json(name = "place") val place: PlaceResponse?,
    @field:Json(name = "recording") val recording: RecordingResponse?,
    @field:Json(name = "release") val release: ReleaseResponse?,
    @field:Json(name = "release_group") val releaseGroup: ReleaseGroupResponse?,
    @field:Json(name = "url") val url: UrlResponse?,
    @field:Json(name = "work") val work: WorkResponse?,
    @field:Json(name = "series") val series: SeriesResponse?
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

    override fun toString() = type
}