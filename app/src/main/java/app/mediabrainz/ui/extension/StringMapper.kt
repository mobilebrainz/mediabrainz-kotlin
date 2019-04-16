package app.mediabrainz.ui.extension

import app.mediabrainz.api.response.ReleaseGroupPrimaryType.*
import app.mediabrainz.api.response.ReleaseGroupSecondaryType.*
import app.mediabrainz.domain.model.ReleaseGroup
import app.mediabrainz.ui.App
import app.mediabrainz.ui.R


object StringMapper {

    fun mapReleaseGroupPrimaryType(releaseGroup: ReleaseGroup): String {
        return when (releaseGroup.primaryType.toLowerCase()) {
            ALBUM.type -> getString(R.string.rt_album)
            BROADCAST.type -> getString(R.string.rt_broadcast)
            EP.type -> getString(R.string.rt_ep)
            SINGLE.type -> getString(R.string.rt_single)
            OTHER.type -> getString(R.string.rt_other)
            else -> ""
        }
    }

    fun mapReleaseGroupSecondaryType(releaseGroup: ReleaseGroup): String {
        val res = App.instance.resources
        val secondaryTypes = releaseGroup.secondaryTypes
        return if (secondaryTypes.isNotEmpty()) {
            when (secondaryTypes[0].toLowerCase()) {
                AUDIOBOOK.type -> getString(R.string.rt_audiobook)
                COMPILATION.type -> getString(R.string.rt_compilation)
                DJ_MIX.type -> getString(R.string.rt_dj_mix)
                INTERVIEW.type -> getString(R.string.rt_interview)
                LIVE.type -> getString(R.string.rt_live)
                MIXTAPE.type -> getString(R.string.rt_mixtape)
                REMIX.type -> getString(R.string.rt_remix)
                SOUNDTRACK.type -> getString(R.string.rt_soundtrack)
                SPOKENWORD.type -> getString(R.string.rt_spokenword)
                STREET.type -> getString(R.string.rt_street)
                else -> ""
            }
        } else ""
    }

    fun mapReleaseGroupOneType(releaseGroup: ReleaseGroup): String {
        val secondaryType = mapReleaseGroupSecondaryType(releaseGroup)
        return if (secondaryType.isNotEmpty()) secondaryType
        else mapReleaseGroupPrimaryType(releaseGroup)
    }

    fun mapReleaseGroupAllType(releaseGroup: ReleaseGroup): String {
        val primaryType = mapReleaseGroupPrimaryType(releaseGroup)
        val secondaryType = mapReleaseGroupSecondaryType(releaseGroup)

        return if (primaryType.isNotEmpty() && secondaryType.isNotEmpty()) "$primaryType/$secondaryType"
        else if (primaryType.isNotEmpty() && secondaryType.isEmpty()) primaryType
        else if (primaryType.isEmpty() && secondaryType.isNotEmpty()) secondaryType
        else getString(R.string.rt_unknown)
    }

}