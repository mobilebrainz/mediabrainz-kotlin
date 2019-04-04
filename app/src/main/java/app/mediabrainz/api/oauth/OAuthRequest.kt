package app.mediabrainz.api.oauth

import app.mediabrainz.api.core.Config
import app.mediabrainz.api.core.WebService
import app.mediabrainz.api.retrofit.OAuthRequestService


class OAuthRequest {

    private fun createJsonRetrofitService() =
        WebService.createJsonRetrofitService(OAuthRequestService::class.java, Config.TEST_WEB_SERVICE)

    fun requestToken(
        code: String, clientId: String, clientSecret: String,
        redirectUri: String, grantType: String
    ) = createJsonRetrofitService().requestToken(code, clientId, clientSecret, redirectUri, grantType)

    fun refreshToken(refreshToken: String, clientId: String, clientSecret: String, grantType: String) =
        createJsonRetrofitService().refreshToken(refreshToken, clientId, clientSecret, grantType)

    fun getUserInfo(accessToken: String) = createJsonRetrofitService().getUserInfo(accessToken)

    fun getTokenInfo(accessToken: String) = createJsonRetrofitService().getTokenInfo(accessToken)
}