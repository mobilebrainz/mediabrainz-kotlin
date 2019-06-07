package app.mediabrainz.domain.model

import androidx.annotation.StringRes
import app.mediabrainz.api.response.TagTypeResponse
import app.mediabrainz.ui.R


class Tag(val name: String, val count: Int): Entity() {

    override fun toString() = name

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Tag

        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }
}

enum class TagType(val type: TagTypeResponse, @StringRes val titleRes: Int) {
    GENRE(TagTypeResponse.GENRE, R.string.tags_tab_genres),
    TAG(TagTypeResponse.TAG, R.string.tags_tab_tags);
}
