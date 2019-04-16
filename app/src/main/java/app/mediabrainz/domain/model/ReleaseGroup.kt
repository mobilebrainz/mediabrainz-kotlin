package app.mediabrainz.domain.model

class ReleaseGroup(
    val mbid: String,
    val name: String,
    // todo: map to ReleaseGroupPrimaryType
    val primaryType: String = "",
    // todo: map to List<ReleaseGroupSecondaryType>
    val secondaryTypes: List<String> = ArrayList(),
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
) : Entity()
