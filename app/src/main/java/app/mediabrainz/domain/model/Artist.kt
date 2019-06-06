package app.mediabrainz.domain.model

import androidx.annotation.StringRes
import app.mediabrainz.api.response.ArtistTypeResponse
import app.mediabrainz.ui.R


class Artist (
    val mbid: String,
    val name: String,
    val disambiguation: String = "",
    val type: ArtistType,
    val country: String = "",
    val gender: String = "",
    val area: Area?,
    val beginArea: Area?,
    val endArea: Area?,
    val lifeSpan: LifeSpan,
    val rating: Rating?,
    val userRating: Rating?,
    override var tags: List<Tag> = ArrayList(),
    override var userTags: List<Tag> = ArrayList(),
    override var genres: List<Tag> = ArrayList(),
    override var userGenres: List<Tag> = ArrayList(),
    val releaseGroups: List<ReleaseGroup> = ArrayList(),

    // relations
    val artistRelations: List<Relation<Artist>> = ArrayList(),
    val urlRelations: List<Relation<Url>> = ArrayList()

) : Entity(), Tagged


enum class ArtistType(val type: ArtistTypeResponse, @StringRes val id: Int) {

    PERSON(ArtistTypeResponse.PERSON, R.string.artist_type_person),
    GROUP(ArtistTypeResponse.GROUP, R.string.artist_type_group),
    ORCHESTRA(ArtistTypeResponse.ORCHESTRA, R.string.artist_type_orchestra),
    CHOIR(ArtistTypeResponse.CHOIR, R.string.artist_type_choir),
    CHARACTER(ArtistTypeResponse.CHARACTER, R.string.artist_type_character),
    OTHER(ArtistTypeResponse.OTHER, R.string.artist_type_other);

    override fun toString() = type.toString()

    companion object {
        fun typeOf(str: String): ArtistType {
            for (value in values()) {
                if (value.type.type.equals(str, true)) return value
            }
            return OTHER
        }
    }
}