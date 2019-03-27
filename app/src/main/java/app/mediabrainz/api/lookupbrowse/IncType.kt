package app.mediabrainz.api.lookupbrowse

object IncType {
    const val ALIASES_INC = "aliases"
    const val ANNOTATION_INC = "annotation"
    const val ARTISTS_INC = "artists"
    const val ARTIST_CREDITS_INC = "artist-credits"
    const val COLLECTIONS_INC = "collections"
    const val DISCIDS_INC = "discids"
    const val ISRCS_INC = "isrcs"
    const val LABELS_INC = "labels"
    const val MEDIA_INC = "media"
    const val RATINGS_INC = "ratings"
    const val RECORDINGS_INC = "recordings"
    const val RELEASES_INC = "releases"
    const val RELEASE_GROUPS_INC = "release-groups"
    const val TAGS_INC = "tags"
    const val GENRES_INC = "genres"
    const val USER_COLLECTIONS_INC = "user-collections"
    const val USER_RATINGS_INC = "user-ratings"
    const val USER_TAGS_INC = "user-tags"
    const val USER_GENRES_INC = "user-genres"
    const val VARIOUS_ARTISTS_INC = "various-artists"
    const val WORKS_INC = "works"

    val AUTHORIZATED_INCS = arrayOf(
        USER_RATINGS_INC,
        USER_TAGS_INC,
        USER_GENRES_INC
    )
}