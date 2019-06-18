package app.mediabrainz.ui

import android.app.Application
import android.content.pm.PackageManager
import app.mediabrainz.domain.OAuthManager
import app.mediabrainz.ui.preference.OAuthPreferences


class App : Application() {

    companion object {
        lateinit var instance: App
        lateinit var CLIENT: String

        fun getVersion(): String =
            try {
                instance.packageManager.getPackageInfo(instance.packageName, 0).versionName
            } catch (e: PackageManager.NameNotFoundException) {
                "unknown"
            }

        fun getPackage(): String =
            try {
                instance.packageManager.getPackageInfo(instance.packageName, 0).packageName
            } catch (e: PackageManager.NameNotFoundException) {
                "unknown"
            }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        CLIENT = getPackage() + "-" + getVersion()
        OAuthManager.oauthStorage = OAuthPreferences
    }

    override fun onTerminate() {
        super.onTerminate()
        OAuthManager.cancelJob()
    }

}