package app.mediabrainz.ui

import android.content.Context
import app.mediabrainz.domain.OAuthManager
import app.mediabrainz.domain.model.AccessToken


object OAuthPreferences : OAuthManager.OAuthStorageInterface {

    override fun write(accessToken: AccessToken) {
        setAccessToken(accessToken.token)
        setRefreshToken(accessToken.refreshToken)
        setExpiresIn(accessToken.expiresIn)
    }

    override fun read() =
        if (isNotEmpty()) AccessToken(getAccessToken(), getExpiresIn(), getRefreshToken())
        else null

    private const val OAUTH_PREFERENCES = "oauth_preferences"
    private const val OAUTH_ACCESS_TOKEN = "OAuthPreferences.OAUTH_ACCESS_TOKEN"
    private const val OAUTH_REFRESH_TOKEN = "OAuthPreferences.REFRESH_TOKEN"
    private const val OAUTH_EXPIRES_IN = "OAuthPreferences.EXPIRES_IN"

    private fun getOAuthPreferences() =
        App.instance.getSharedPreferences(OAUTH_PREFERENCES, Context.MODE_PRIVATE)

    fun clear() {
        getOAuthPreferences().edit().clear().apply()
    }

    fun isNotEmpty() =
        getAccessToken().isNotBlank() && getRefreshToken().isNotBlank()

    fun setAccessToken(accessToken: String) {
        getOAuthPreferences().edit()
            .putString(OAUTH_ACCESS_TOKEN, accessToken).apply()
    }

    fun getAccessToken() =
        getOAuthPreferences().getString(OAUTH_ACCESS_TOKEN, "") ?: ""

    fun setRefreshToken(refreshToken: String) {
        getOAuthPreferences().edit()
            .putString(OAUTH_REFRESH_TOKEN, refreshToken).apply()
    }

    fun getRefreshToken() =
        getOAuthPreferences().getString(OAUTH_REFRESH_TOKEN, "") ?: ""


    fun setExpiresIn(expiresIn: Long) {
        getOAuthPreferences().edit()
            .putLong(OAUTH_EXPIRES_IN, expiresIn).apply()
    }

    fun getExpiresIn() =
        getOAuthPreferences().getLong(OAUTH_EXPIRES_IN, 0)

}