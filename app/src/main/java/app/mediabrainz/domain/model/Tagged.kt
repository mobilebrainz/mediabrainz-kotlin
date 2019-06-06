package app.mediabrainz.domain.model


interface Tagged {

    var tags: List<Tag>
    var userTags: List<Tag>
    var genres: List<Tag>
    var userGenres: List<Tag>

}