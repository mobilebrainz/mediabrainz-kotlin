package app.mediabrainz.domain.repository.dbRepository

import android.app.Application
import android.os.AsyncTask
import app.mediabrainz.db.dao.SuggestionDao
import app.mediabrainz.db.database.SuggestionDatabase
import app.mediabrainz.db.entity.Suggestion
import app.mediabrainz.db.entity.SuggestionField


class SuggestionRepository(application: Application) {

    private var suggestionDao: SuggestionDao

    init {
        val database: SuggestionDatabase =
            SuggestionDatabase.getInstance(application.applicationContext)!!
        suggestionDao = database.suggestionDao()
    }

    fun insert(suggestion: Suggestion, postAction: () -> Unit = {}) {
        InsertSuggestionTask(suggestionDao, postAction).execute(suggestion)
    }

    fun deleteAll(postAction: () -> Unit = {}) {
        DeleteAllSuggestionsTask(suggestionDao, postAction).execute()
    }

    fun findByWordAndField(word: String, field: SuggestionField): List<Suggestion> {
        return suggestionDao.findByWordAndField("$word%", field.field)
    }

    fun getAll() = suggestionDao.getAll()

    fun getByField(suggestionField: SuggestionField) =
        suggestionDao.getByField(suggestionField.field)

    private class InsertSuggestionTask(
        val asyncSuggestionDao: SuggestionDao,
        val postAction: () -> Unit
    ) : AsyncTask<Suggestion, Unit, Unit>() {

        override fun doInBackground(vararg suggestions: Suggestion): Unit? {
            asyncSuggestionDao.insert(*suggestions)
            return null
        }

        override fun onPostExecute(aVoid: Unit?) {
            super.onPostExecute(aVoid)
            postAction.invoke()
        }
    }

    private class DeleteAllSuggestionsTask(
        val asyncSuggestionDao: SuggestionDao,
        val postAction: () -> Unit
    ) : AsyncTask<Unit, Unit, Unit>() {

        override fun doInBackground(vararg voids: Unit): Unit? {
            asyncSuggestionDao.deleteAll()
            return null
        }

        override fun onPostExecute(aVoid: Unit?) {
            super.onPostExecute(aVoid)
            postAction.invoke()
        }
    }
}