package app.mediabrainz.ui.preference

import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import app.mediabrainz.ui.App


object GlobalPreferences {

    const val SUGGESTIONS = "search_suggestions"
    const val LOAD_IMAGES = "load_images"
    const val RELEASE_GROUP_OFFICIAL = "release_group_official"
    const val PROPAGATE_ARTIST_TAGS = "propagate_artist_tags"

    private fun getDefaultPreferences(): SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(App.instance)

    fun clear() {
        getDefaultPreferences().edit().clear().apply()
    }

    fun setSearchSuggestionsEnabled(enabled: Boolean) {
        getDefaultPreferences().edit().putBoolean(SUGGESTIONS, enabled).apply()
    }

    fun isSearchSuggestionsEnabled() =
        getDefaultPreferences().getBoolean(SUGGESTIONS, true)

    fun setLoadImagesEnabled(enabled: Boolean) {
        getDefaultPreferences().edit().putBoolean(LOAD_IMAGES, enabled).apply()
    }

    fun isLoadImagesEnabled() =
        getDefaultPreferences().getBoolean(LOAD_IMAGES, true)

    fun setReleaseGroupOfficial(enabled: Boolean) {
        getDefaultPreferences().edit().putBoolean(RELEASE_GROUP_OFFICIAL, enabled).apply()
    }

    fun isReleaseGroupOfficial() =
        getDefaultPreferences().getBoolean(RELEASE_GROUP_OFFICIAL, true)

    fun setPropagateArtistTags(enabled: Boolean) {
        getDefaultPreferences().edit().putBoolean(PROPAGATE_ARTIST_TAGS, enabled).apply()
    }

    fun isPropagateArtistTags() =
        getDefaultPreferences().getBoolean(PROPAGATE_ARTIST_TAGS, false)

    fun registerOnSharedPreferenceChangeListener(listener: SharedPreferences.OnSharedPreferenceChangeListener) {
        getDefaultPreferences().registerOnSharedPreferenceChangeListener(listener)
    }

    fun unregisterOnSharedPreferenceChangeListener(listener: SharedPreferences.OnSharedPreferenceChangeListener) {
        getDefaultPreferences().unregisterOnSharedPreferenceChangeListener(listener)
    }

}