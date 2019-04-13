package app.mediabrainz.ui.viewmodel.dbRepository

import androidx.lifecycle.ViewModel
import app.mediabrainz.db.entity.Suggestion
import app.mediabrainz.db.entity.SuggestionField
import app.mediabrainz.domain.repository.dbRepository.SuggestionRepository
import app.mediabrainz.ui.App


class SuggestionViewModel : ViewModel() {

    private var repository: SuggestionRepository = SuggestionRepository(App.instance)

    fun insert(word: String, suggestionField: SuggestionField) {
        //if (MediaBrainzApp.getPreferences().isSearchSuggestionsEnabled()) {
        repository.insert(Suggestion(word, suggestionField))
        //}
    }

    /*
    fun deleteAll(postAction: () -> Unit = {}) {
        repository.deleteAll(postAction)
    }
    */

    fun getAll() = repository.getAll()

    fun getByField(suggestionField: SuggestionField) = repository.getByField(suggestionField)

}