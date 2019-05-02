package app.mediabrainz.ui.fragment

import android.content.SharedPreferences
import android.os.Bundle
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import app.mediabrainz.ui.R
import com.google.android.material.snackbar.Snackbar


class SettingsFragment :
    PreferenceFragmentCompat(),
    SharedPreferences.OnSharedPreferenceChangeListener {

    private val CLEAR_SUGGESTIONS = "clear_suggestions"
    private val CLEAR_RECOMMENDS = "clear_recommends"

    private lateinit var prefs: SharedPreferences

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.preferences)

        findPreference<Preference>(CLEAR_SUGGESTIONS)?.setOnPreferenceClickListener { preference ->
            if (preference.key == CLEAR_SUGGESTIONS) {
                clearSuggestionHistory()
                true
            } else false
        }

        findPreference<Preference>(CLEAR_RECOMMENDS)?.setOnPreferenceClickListener { preference ->
            if (preference.key == CLEAR_RECOMMENDS) {
                clearRecommends()
                true
            } else false
        }
    }

    private fun clearSuggestionHistory() {
        //todo: make progress?
        //todo: remove to ViewModel?
        //SuggestionRepository().deleteAll({ showSnackbar(R.string.toast_search_cleared) })
    }

    private fun clearRecommends() {
        //todo: make progress?
        //todo: remove to ViewModel?
        //RecommendRepository().deleteAll({ showSnackbar(R.string.toast_recommends_cleared) })
    }

    override fun onStart() {
        super.onStart()
        prefs = PreferenceManager.getDefaultSharedPreferences(context)
        prefs.registerOnSharedPreferenceChangeListener(this)
    }

    override fun onStop() {
        super.onStop()
        prefs.unregisterOnSharedPreferenceChangeListener(this)
    }


    override fun onSharedPreferenceChanged(p0: SharedPreferences?, p1: String?) {
    }

    private fun showSnackbar(res: Int) {
        view?.let {
            Snackbar.make(it, res, Snackbar.LENGTH_LONG).show()
        }
    }

}