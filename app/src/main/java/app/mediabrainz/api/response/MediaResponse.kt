package app.mediabrainz.api.response

import com.squareup.moshi.Json


data class MediaResponse(
    @field:Json(name = "title") val title: String,
    @field:Json(name = "format") val format: String?,
    @field:Json(name = "format-id") val formatId: String?,
    @field:Json(name = "position") val position: Int?,
    @field:Json(name = "disc-count") val discCount: Int?,
    @field:Json(name = "track-count") val trackCount: Int?,
    @field:Json(name = "track-offset") val trackOffset: Int?,
    @field:Json(name = "tracks") val tracks: List<TrackResponse>?,
    /**
     * inc=discs
     */
    @field:Json(name = "discs") val discs: List<DiscResponse>?
)

// TODO: check formats
// https://musicbrainz.org/doc/Release/Format
// https://musicbrainz.org/statistics/formats
enum class MediaFormatTypeResponse(val format: String) {
    CD("cd"),
    VINYL("vinyl"),
    VINYL_12("12\" Vinyl"),
    CASSETTE("cassette"),
    DVD("dvd"),
    DIGITAL_MEDIA("digital media"),
    SACD("sacd"),
    DUALDISC("laserdisc"),
    LASERDISC("laserdisc"),
    MINIDISC("minidisc"),
    CARTRIDGE("cartridge"),
    REEL_TO_REEL("reel-to-reel"),
    DAT("dat"),
    OTHER("other"),
    WAX_CYLINDER("wax cylinder"),
    PIANO_ROLL("piano roll"),
    DIGITAL_COMPACT_CASSETTE("digital compact cassette"),
    VHS("vhs"),
    VIDEO_CD("video-cd"),
    SUPER_VIDEO_CD("super video-cd"),
    BETAMAX("betamax"),
    HD_COMPATIBLE_DIGITAL("hd compatible digital"),
    USB_FLASH_DRIVE("usb flash drive"),
    SLOTMUSIC("slotmusic"),
    UNIVERSAL_MEDIA_DISC("universal media disc"),
    HD_DVD("hd-dvd"),
    DVD_AUDIO("dvd-audio"),
    DVD_VIDEO("dvd-video"),
    BLUE_RAY("blu-ray");

    override fun toString() = format
}
