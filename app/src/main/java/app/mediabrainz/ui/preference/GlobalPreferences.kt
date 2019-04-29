package app.mediabrainz.ui.preference

import android.content.Context
import android.content.SharedPreferences
import app.mediabrainz.ui.App


object GlobalPreferences {

    private const val GLOBAL_PREFERENCES = "global_preferences"
    private const val SUGGESTIONS = "GlobalPreferences.search_suggestions"
    private const val LOAD_IMAGES = "GlobalPreferences.load_images"
    private const val RELEASE_GROUP_OFFICIAL = "GlobalPreferences.release_group_official"
    private const val PROPAGATE_ARTIST_TAGS = "GlobalPreferences.propagate_artist_tags"

    private fun getPreferences() =
        App.instance.getSharedPreferences(GLOBAL_PREFERENCES, Context.MODE_PRIVATE)

    fun clear() {
        getPreferences().edit().clear().apply()
    }

    fun setSearchSuggestionsEnabled(enabled: Boolean) {
        getPreferences().edit().putBoolean(SUGGESTIONS, enabled).apply()
    }

    fun isSearchSuggestionsEnabled() =
        getPreferences().getBoolean(SUGGESTIONS, true)

    fun setLoadImagesEnabled(enabled: Boolean) {
        getPreferences().edit().putBoolean(LOAD_IMAGES, enabled).apply()
    }

    fun isLoadImagesEnabled() =
        getPreferences().getBoolean(LOAD_IMAGES, true)

    fun setReleaseGroupOfficial(enabled: Boolean) {
        getPreferences().edit().putBoolean(RELEASE_GROUP_OFFICIAL, enabled).apply()
    }

    fun isReleaseGroupOfficial() =
        getPreferences().getBoolean(RELEASE_GROUP_OFFICIAL, false)

    fun setPropagateArtistTags(enabled: Boolean) {
        getPreferences().edit().putBoolean(PROPAGATE_ARTIST_TAGS, enabled).apply()
    }

    fun isPropagateArtistTags() =
        getPreferences().getBoolean(PROPAGATE_ARTIST_TAGS, false)

    fun registerOnSharedPreferenceChangeListener(listener: SharedPreferences.OnSharedPreferenceChangeListener) {
        getPreferences().registerOnSharedPreferenceChangeListener(listener)
    }

    fun unregisterOnSharedPreferenceChangeListener(listener: SharedPreferences.OnSharedPreferenceChangeListener) {
        getPreferences().unregisterOnSharedPreferenceChangeListener(listener)
    }

}