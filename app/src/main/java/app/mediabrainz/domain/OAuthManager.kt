package app.mediabrainz.domain

import app.mediabrainz.api.ApiRequestProvider
import app.mediabrainz.api.core.Config
import app.mediabrainz.domain.mapper.AccessTokenMapper
import app.mediabrainz.domain.model.AccessToken
import kotlinx.coroutines.*
import retrofit2.HttpException


object OAuthManager {

    const val OAUTH_SCOPE = "profile email tag rating collection submit_isrc submit_barcode"
    const val OAUTH_CLIENT_ID = "KoSg5TjyO7cMYXjt2kvz8g"
    const val OAUTH_CLIENT_SECRET = "XL84wz60G4tscOLZ4b2Leg"
    const val OAUTH_REDIRECT_URI = "org.metabrainz.mobile://oauth"
    const val OAUTH_RESPONSE_TYPE = "code"
    const val OAUTH_AUTHORIZE_GRANT_TYPE = "authorization_code"
    const val OAUTH_REFRESH_GRANT_TYPE = "refresh_token"

    interface OAuthStorageInterface {
        fun write(accessToken: AccessToken)
        fun read(): AccessToken?
    }

    var oauthStorage: OAuthStorageInterface? = null
        set(value) {
            field = value
            accessToken = value?.read()
        }

    var accessToken: AccessToken? = null
        set(value) {
            value?.let {
                field = value
                oauthStorage?.write(value)
            }
        }

    var refreshing = false
    var isError = false

    private val job = Job()
    private val scope = CoroutineScope(Dispatchers.IO + job)
    //private val scope = CoroutineScope(Dispatchers.Default + job)

    private fun refresh() {
        accessToken?.apply {
            isError = true
            refreshing = true
            scope.launch {
                try {
                    val deferred = ApiRequestProvider.createOAuthRequest()
                        .refreshToken(
                            refreshToken,
                            OAUTH_CLIENT_ID,
                            OAUTH_CLIENT_SECRET,
                            OAUTH_REFRESH_GRANT_TYPE
                        )
                    val response = deferred.await()
                    response.body()?.let {
                        if (response.code() == 200) {
                            accessToken = AccessTokenMapper().mapTo(it)
                            isError = false
                        }
                    }
                    refreshing = false
                } catch (e: HttpException) {
                    refreshing = false
                } catch (e: Throwable) {
                    refreshing = false
                }
            }
        }
    }

    suspend fun refreshToken() {
        accessToken?.apply {
            if (!refreshing && System.currentTimeMillis() > expiresIn) refresh()
            while (refreshing) {
                delay(100)
            }
        }
    }

    fun cancelJob() {
        job.cancel()
    }

    fun getAuthorizeUrl() = Config.WEB_SERVICE + Config.OAUTH_AUTHORIZE_QUERY

}