package app.mediabrainz.ui

import android.app.Application
import app.mediabrainz.domain.OAuthManager
import app.mediabrainz.ui.preference.OAuthPreferences


class App : Application() {

    companion object {
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        OAuthManager.oauthStorage = OAuthPreferences
    }

    override fun onTerminate() {
        super.onTerminate()
        OAuthManager.cancelJob()
    }

}