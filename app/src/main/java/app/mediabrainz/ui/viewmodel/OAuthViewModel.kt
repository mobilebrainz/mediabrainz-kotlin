package app.mediabrainz.ui.viewmodel

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.mediabrainz.domain.OAuthManager
import app.mediabrainz.domain.OAuthManager.OAUTH_AUTHORIZE_GRANT_TYPE
import app.mediabrainz.domain.OAuthManager.OAUTH_CLIENT_ID
import app.mediabrainz.domain.OAuthManager.OAUTH_CLIENT_SECRET
import app.mediabrainz.domain.OAuthManager.OAUTH_REDIRECT_URI
import app.mediabrainz.domain.OAuthManager.OAUTH_REFRESH_GRANT_TYPE
import app.mediabrainz.domain.OAuthManager.OAUTH_RESPONSE_TYPE
import app.mediabrainz.domain.OAuthManager.OAUTH_SCOPE
import app.mediabrainz.domain.model.AccessToken
import app.mediabrainz.domain.model.TokenInfo
import app.mediabrainz.domain.model.UserInfo
import app.mediabrainz.domain.repository.OAuthRepository
import app.mediabrainz.domain.repository.Resource


class OAuthViewModel : ViewModel() {

    val accessToken: MutableLiveData<Resource<AccessToken>> = MutableLiveData()
    val userInfo: MutableLiveData<Resource<UserInfo>> = MutableLiveData()
    val tokenInfo: MutableLiveData<Resource<TokenInfo>> = MutableLiveData()

    private val repository: OAuthRepository = OAuthRepository()

    fun getAuthorizationUri() = Uri.parse(
        OAuthManager.getAuthorizeUrl()
                + "?response_type=$OAUTH_RESPONSE_TYPE"
                + "&client_id=$OAUTH_CLIENT_ID"
                + "&redirect_uri=$OAUTH_REDIRECT_URI"
                + "&scope=$OAUTH_SCOPE"
    )

    fun requestToken(code: String) {
        repository.requestToken(
            accessToken,
            code,
            OAUTH_CLIENT_ID,
            OAUTH_CLIENT_SECRET,
            OAUTH_REDIRECT_URI,
            OAUTH_AUTHORIZE_GRANT_TYPE
        )
    }

    fun refreshToken(refreshToken: String) {
        repository.refreshToken(
            accessToken,
            refreshToken,
            OAUTH_CLIENT_ID,
            OAUTH_CLIENT_SECRET,
            OAUTH_REFRESH_GRANT_TYPE
        )
    }

    fun getTokenInfo(accessToken: String) {
        repository.getTokenInfo(tokenInfo, accessToken)
    }

    fun getUserInfo(accessToken: String) {
        repository.getUserInfo(userInfo, accessToken)
    }

    override fun onCleared() {
        super.onCleared()
        repository.cancelJob()
    }

}