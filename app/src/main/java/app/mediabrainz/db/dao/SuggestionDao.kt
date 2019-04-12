package app.mediabrainz.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import app.mediabrainz.db.entity.Suggestion
import app.mediabrainz.db.entity.SuggestionTable.TABLE_NAME


@Dao
interface SuggestionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg suggestions: Suggestion)

    @Query("DELETE FROM $TABLE_NAME")
    fun deleteAll()

    @Query("SELECT * from $TABLE_NAME WHERE word LIKE :word AND field = :field")
    fun findSuggestionsByWordAndField(word: String, field: String): List<Suggestion>

}