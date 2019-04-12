package app.mediabrainz.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import app.mediabrainz.db.entity.SuggestionTable.TABLE_FIELD_FIELD
import app.mediabrainz.db.entity.SuggestionTable.TABLE_FIELD_WORD
import app.mediabrainz.db.entity.SuggestionTable.TABLE_NAME


@Entity(tableName = TABLE_NAME, primaryKeys = [TABLE_FIELD_WORD, TABLE_FIELD_FIELD])
data class Suggestion(
    @ColumnInfo(name = TABLE_FIELD_WORD) var word: String,
    @ColumnInfo(name = TABLE_FIELD_FIELD) var field: String
) {
    @Ignore
    constructor(word: String, suggestionField: SuggestionField) :
            this(word, suggestionField.field)

    override fun toString() = word
}

object SuggestionTable {
    const val TABLE_NAME = "suggestions"
    const val TABLE_FIELD_WORD = "word"
    const val TABLE_FIELD_FIELD = "field"
}

enum class SuggestionField(val field: String) {
    ARTIST("artist"),
    ALBUM("album"),
    TRACK("track"),
    TAG("tag"),
    USER("user");

    override fun toString() = field
}