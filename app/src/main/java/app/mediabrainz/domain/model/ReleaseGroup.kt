package app.mediabrainz.domain.model

import androidx.annotation.StringRes
import app.mediabrainz.api.response.ReleaseGroupPrimaryType
import app.mediabrainz.api.response.ReleaseGroupSecondaryType
import app.mediabrainz.ui.R
import app.mediabrainz.ui.extension.getString


class ReleaseGroup(
    val mbid: String,
    val name: String,
    val primaryType: RGPrimaryType?,
    val secondaryTypes: List<RGSecondaryType>,
    val disambiguation: String = "",
    // todo: map to locale data
    val firstReleaseDate: String = "",
    val releases: List<Release> = ArrayList(),
    val artistCredits: List<ArtistCredit> = ArrayList(),
    val rating: Rating?,
    val userRating: Rating?,
    val tags: List<Tag> = ArrayList(),
    val userTags: List<Tag> = ArrayList(),
    val genres: List<Tag> = ArrayList(),
    val userGenres: List<Tag> = ArrayList()
) : Entity() {

    fun getPrimaryTypeString() =
        if (primaryType != null) getString(primaryType.id) else ""


    fun getFirstSecondaryTypeString() =
        if (secondaryTypes.isNotEmpty()) {
            getString(secondaryTypes[0].id)
        } else ""


    fun getFirstType(): String {
        val secondaryType = getFirstSecondaryTypeString()
        return if (secondaryType.isNotEmpty()) secondaryType
        else getPrimaryTypeString()
    }

    fun getAllType(): String {
        val prType = getPrimaryTypeString()
        val secType = getFirstSecondaryTypeString()

        return if (prType.isNotEmpty() && secType.isNotEmpty()) "$prType/$secType"
        else if (prType.isNotEmpty() && secType.isEmpty()) prType
        else if (prType.isEmpty() && secType.isNotEmpty()) secType
        else getString(R.string.rt_unknown)
    }

}


enum class RGPrimaryType(val type: ReleaseGroupPrimaryType, @StringRes val id: Int) {

    ALBUM(ReleaseGroupPrimaryType.ALBUM, R.string.rt_album),
    SINGLE(ReleaseGroupPrimaryType.SINGLE, R.string.rt_single),
    EP(ReleaseGroupPrimaryType.EP, R.string.rt_ep),
    BROADCAST(ReleaseGroupPrimaryType.BROADCAST, R.string.rt_broadcast),
    OTHER(ReleaseGroupPrimaryType.OTHER, R.string.rt_other);

    override fun toString() = type.toString()

    companion object {
        fun typeOf(str: String?): RGPrimaryType? {
            str?.let {
                for (value in values()) {
                    if (value.type.type.equals(it, true)) return value
                }
            }
            return null
        }
    }
}

enum class RGSecondaryType(val type: ReleaseGroupSecondaryType, @StringRes val id: Int) {

    COMPILATION(ReleaseGroupSecondaryType.COMPILATION, R.string.rt_compilation),
    SOUNDTRACK(ReleaseGroupSecondaryType.SOUNDTRACK, R.string.rt_soundtrack),
    SPOKENWORD(ReleaseGroupSecondaryType.SPOKENWORD, R.string.rt_spokenword),
    INTERVIEW(ReleaseGroupSecondaryType.INTERVIEW, R.string.rt_interview),
    AUDIOBOOK(ReleaseGroupSecondaryType.AUDIOBOOK, R.string.rt_audiobook),
    LIVE(ReleaseGroupSecondaryType.LIVE, R.string.rt_live),
    REMIX(ReleaseGroupSecondaryType.REMIX, R.string.rt_remix),
    DJ_MIX(ReleaseGroupSecondaryType.DJ_MIX, R.string.rt_dj_mix),
    MIXTAPE(ReleaseGroupSecondaryType.MIXTAPE, R.string.rt_mixtape),
    STREET(ReleaseGroupSecondaryType.STREET, R.string.rt_street);

    override fun toString() = type.toString()

    companion object {
        fun typeOf(str: String): RGSecondaryType? {
            for (value in values()) {
                if (value.type.type.equals(str, true)) return value
            }
            return null
        }
    }
}
