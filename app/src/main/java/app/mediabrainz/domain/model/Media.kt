package app.mediabrainz.domain.model

import android.content.Context
import androidx.annotation.StringRes
import app.mediabrainz.api.response.MediaFormatTypeResponse
import app.mediabrainz.ui.R
import java.util.*


class Media(
    val title: String,
    val format: MediaFormatType,
    val trackCount: Int = 0
) {

    companion object {

        private fun getFormatCounts(medias: List<Media>): Map<MediaFormatType, Int> {
            val formatCounts = HashMap<MediaFormatType, Int>()
            for (media in medias) {
                val count = formatCounts[media.format]
                formatCounts[media.format] = if (count == null) 1 else count + 1
            }
            return formatCounts
        }

        fun buildReleaseFormatsString(context: Context, medias: List<Media>): String {
            val formatCounts = getFormatCounts(medias)
            val formatKeys = formatCounts.keys
            if (formatKeys.isEmpty()) {
                return ""
            }

            val sb = StringBuilder()
            for (format in formatKeys) {
                val number = formatCounts[format]
                if (number != null && number > 1) {
                    sb.append(number.toString() + "x")
                }
                sb.append(context.resources.getString(format.id) + ", ")
            }
            return sb.substring(0, sb.length - 2)
        }
    }
}

enum class MediaFormatType(val type: MediaFormatTypeResponse, @StringRes val id: Int) {

    CD(MediaFormatTypeResponse.CD, R.string.fm_cd),
    VINYL(MediaFormatTypeResponse.VINYL, R.string.fm_vinyl),
    VINYL_12(MediaFormatTypeResponse.VINYL_12, R.string.fm_vinyl_12),
    CASSETTE(MediaFormatTypeResponse.CASSETTE, R.string.fm_cassette),
    DVD(MediaFormatTypeResponse.DVD, R.string.fm_dvd),
    DIGITAL_MEDIA(MediaFormatTypeResponse.DIGITAL_MEDIA, R.string.fm_dm),
    SACD(MediaFormatTypeResponse.SACD, R.string.fm_sacd),
    DUALDISC(MediaFormatTypeResponse.DUALDISC, R.string.fm_dd),
    LASERDISC(MediaFormatTypeResponse.LASERDISC, R.string.fm_ld),
    MINIDISC(MediaFormatTypeResponse.MINIDISC, R.string.fm_md),
    CARTRIDGE(MediaFormatTypeResponse.CARTRIDGE, R.string.fm_cartridge),
    REEL_TO_REEL(MediaFormatTypeResponse.REEL_TO_REEL, R.string.fm_rtr),
    DAT(MediaFormatTypeResponse.DAT, R.string.fm_dat),
    OTHER(MediaFormatTypeResponse.OTHER, R.string.fm_other),
    WAX_CYLINDER(MediaFormatTypeResponse.WAX_CYLINDER, R.string.fm_wax),
    PIANO_ROLL(MediaFormatTypeResponse.PIANO_ROLL, R.string.fm_pr),
    DIGITAL_COMPACT_CASSETTE(MediaFormatTypeResponse.DIGITAL_COMPACT_CASSETTE, R.string.fm_dcc),
    VHS(MediaFormatTypeResponse.VHS, R.string.fm_vhs),
    VIDEO_CD(MediaFormatTypeResponse.VIDEO_CD, R.string.fm_vcd),
    SUPER_VIDEO_CD(MediaFormatTypeResponse.SUPER_VIDEO_CD, R.string.fm_svcd),
    BETAMAX(MediaFormatTypeResponse.BETAMAX, R.string.fm_bm),
    HD_COMPATIBLE_DIGITAL(MediaFormatTypeResponse.HD_COMPATIBLE_DIGITAL, R.string.fm_hdcd),
    USB_FLASH_DRIVE(MediaFormatTypeResponse.USB_FLASH_DRIVE, R.string.fm_usb),
    SLOTMUSIC(MediaFormatTypeResponse.SLOTMUSIC, R.string.fm_sm),
    UNIVERSAL_MEDIA_DISC(MediaFormatTypeResponse.UNIVERSAL_MEDIA_DISC, R.string.fm_umd),
    HD_DVD(MediaFormatTypeResponse.HD_DVD, R.string.fm_hddvd),
    DVD_AUDIO(MediaFormatTypeResponse.DVD_AUDIO, R.string.fm_dvda),
    DVD_VIDEO(MediaFormatTypeResponse.DVD_VIDEO, R.string.fm_dvdv),
    BLUE_RAY(MediaFormatTypeResponse.BLUE_RAY, R.string.fm_br);

    override fun toString() = type.toString()

    companion object {
        fun typeOf(str: String): MediaFormatType {
            for (value in values()) {
                if (value.type.format.equals(str, true)) return value
            }
            return OTHER
        }
    }
}