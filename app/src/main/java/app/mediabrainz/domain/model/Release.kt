package app.mediabrainz.domain.model

import androidx.annotation.StringRes
import app.mediabrainz.api.response.ReleaseStatusResponse
import app.mediabrainz.ui.R

class Release(
    val mbid: String,
    val name: String,
    val status: ReleaseStatus,
    val date: String = "",
    val barcode: String = "",
    val country: String = "",
    val coverArtArchive: CoverArtArchive,
    val labels: List<LabelInfo> = ArrayList(),
    val media: List<Media> = ArrayList(),
    override var tags: List<Tag> = ArrayList(),
    override var userTags: List<Tag> = ArrayList(),
    override var genres: List<Tag> = ArrayList(),
    override var userGenres: List<Tag> = ArrayList()

) : Entity(), Tagged {

}

enum class ReleaseStatus(val type: ReleaseStatusResponse, @StringRes val id: Int) {
    OFFICIAL(ReleaseStatusResponse.OFFICIAL, R.string.release_status_offical),
    PROMOTIONAL(ReleaseStatusResponse.PROMOTIONAL, R.string.release_status_promotional),
    BOOTLEG(ReleaseStatusResponse.BOOTLEG, R.string.release_status_bootleg),
    PSEUDO_RELEASE(ReleaseStatusResponse.PSEUDO_RELEASE, R.string.release_status_preudo_release),
    EMPTY(ReleaseStatusResponse.EMPTY, -1);

    override fun toString() = type.toString()

    companion object {
        fun typeOf(str: String): ReleaseStatus {
            for (value in values()) {
                if (value.type.status.equals(str, true)) return value
            }
            return EMPTY
        }
    }
}