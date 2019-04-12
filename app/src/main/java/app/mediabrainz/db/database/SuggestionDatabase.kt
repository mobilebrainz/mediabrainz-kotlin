package app.mediabrainz.db.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import app.mediabrainz.db.dao.SuggestionDao
import app.mediabrainz.db.entity.Suggestion


@Database(entities = [Suggestion::class], version = 1)
abstract class SuggestionDatabase : RoomDatabase() {

    abstract fun suggestionDao(): SuggestionDao

    companion object {

        const val SUGGESTION_DATABASE = "suggestiondb"

        @Volatile
        private var instance: SuggestionDatabase? = null

        fun getInstance(context: Context): SuggestionDatabase? {
            if (instance == null) {
                synchronized(SuggestionDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        SuggestionDatabase::class.java,
                        SUGGESTION_DATABASE
                    )
                        .fallbackToDestructiveMigration().build()
                }
            }
            return instance
        }

        fun destroyInstance() {
            instance = null
        }
    }

}