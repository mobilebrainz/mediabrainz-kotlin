package app.mediabrainz.db.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import app.mediabrainz.db.dao.SuggestionDao1
import app.mediabrainz.db.entity.Suggestion


@Database(entities = [Suggestion::class], version = 1)
abstract class SuggestionDatabase1 : RoomDatabase() {

    abstract fun suggestionDao(): SuggestionDao1

    companion object {

        const val SUGGESTION_DATABASE = "suggestiondb"

        private var instance: SuggestionDatabase1? = null

        fun getInstance(context: Context): SuggestionDatabase1? {
            if (instance == null) {
                synchronized(SuggestionDatabase1::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        SuggestionDatabase1::class.java,
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