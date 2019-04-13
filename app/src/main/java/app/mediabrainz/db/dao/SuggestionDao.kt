package app.mediabrainz.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import app.mediabrainz.db.entity.Suggestion
import app.mediabrainz.db.entity.SuggestionTable.TABLE_FIELD_FIELD
import app.mediabrainz.db.entity.SuggestionTable.TABLE_FIELD_WORD
import app.mediabrainz.db.entity.SuggestionTable.TABLE_NAME


@Dao
interface SuggestionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg suggestions: Suggestion)

    @Query("DELETE FROM $TABLE_NAME")
    fun deleteAll()

    @Query("SELECT * from $TABLE_NAME WHERE $TABLE_FIELD_WORD LIKE :word AND $TABLE_FIELD_FIELD = :field")
    fun findByWordAndField(word: String, field: String): List<Suggestion>

    @Query("SELECT * from $TABLE_NAME WHERE $TABLE_FIELD_FIELD = :field")
    fun getByField(field: String): LiveData<List<Suggestion>>

    @Query("SELECT * FROM $TABLE_NAME")
    fun getAll(): LiveData<List<Suggestion>>

}