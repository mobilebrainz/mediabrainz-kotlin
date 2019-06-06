package app.mediabrainz.domain.model


class Recording(
    val mbid: String,
    val name: String,
    override var tags: List<Tag> = ArrayList(),
    override var userTags: List<Tag> = ArrayList(),
    override var genres: List<Tag> = ArrayList(),
    override var userGenres: List<Tag> = ArrayList()
) : Entity(), Tagged {

}