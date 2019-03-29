package app.mediabrainz.ui

import android.content.Context


object OAuthPreferences {

    private const val OAUTH_PREFERENCES = "oauth_preferences"
    private const val OAUTH_ACCESS_TOKEN = "OAuthPreferences.OAUTH_ACCESS_TOKEN"
    private const val OAUTH_REFRESH_TOKEN = "OAuthPreferences.REFRESH_TOKEN"
    private const val OAUTH_EXPIRES_IN = "OAuthPreferences.EXPIRES_IN"

    private fun getOAuthPreferences() =
        App.instance.getSharedPreferences(OAUTH_PREFERENCES, Context.MODE_PRIVATE)

    fun clear() {
        getOAuthPreferences().edit().clear().apply()
    }

    fun init(accessToken: String, refreshToken: String, expiresIn: Long) {
        setAccessToken(accessToken)
        setRefreshToken(refreshToken)
        setExpiresIn(expiresIn)
    }

    fun setAccessToken(accessToken: String) {
        getOAuthPreferences().edit()
            .putString(OAUTH_ACCESS_TOKEN, accessToken).apply()
    }

    fun getAccessToken() =
        getOAuthPreferences().getString(OAUTH_ACCESS_TOKEN, "")

    fun setRefreshToken(refreshToken: String) {
        getOAuthPreferences().edit()
            .putString(OAUTH_REFRESH_TOKEN, refreshToken).apply()
    }

    fun getRefreshToken() =
        getOAuthPreferences().getString(OAUTH_REFRESH_TOKEN, "")


    fun setExpiresIn(expiresIn: Long) {
        getOAuthPreferences().edit()
            .putLong(OAUTH_EXPIRES_IN, expiresIn).apply()
    }

    fun getExpiresIn() =
        getOAuthPreferences().getLong(OAUTH_EXPIRES_IN, 0)

}