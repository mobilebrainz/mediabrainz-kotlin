package app.mediabrainz.ui.viewmodel

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.mediabrainz.domain.model.AccessToken
import app.mediabrainz.domain.model.TokenInfo
import app.mediabrainz.domain.model.UserInfo
import app.mediabrainz.domain.repository.OAuthRepository
import app.mediabrainz.domain.repository.Resource


class OAuthViewModel : ViewModel() {

    companion object {

        /*
        //old authorization
        const val OAUTH_SCOPE = "profile email tag rating collection submit_isrc submit_barcode"
        const val OAUTH_CLIENT_ID = "KoSg5TjyO7cMYXjt2kvz8g"
        const val OAUTH_CLIENT_SECRET = "XL84wz60G4tscOLZ4b2Leg"
        const val OAUTH_REDIRECT_URI = "urn:ietf:wg:oauth:2.0:oob"
        */

        //new test authorization
        const val OAUTH_SCOPE = "profile collection"
        const val OAUTH_CLIENT_ID = "X2rIt5DgKkd0W6XsSjBqWg"
        const val OAUTH_CLIENT_SECRET = "FA5Fa8zEJQs43Jpig_rtgw"
        const val OAUTH_REDIRECT_URI = "org.metabrainz.mobile://oauth"


        const val OAUTH_RESPONSE_TYPE = "code"
        const val OAUTH_GRANT_TYPE = "authorization_code"
    }

    val accessToken: MutableLiveData<Resource<AccessToken>> = MutableLiveData()
    val userInfo: MutableLiveData<Resource<UserInfo>> = MutableLiveData()
    val tokenInfo: MutableLiveData<Resource<TokenInfo>> = MutableLiveData()

    private val repository: OAuthRepository = OAuthRepository()

    fun getAuthorizationUri() = Uri.parse(
        repository.getAuthorizeUrl()
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
            OAUTH_GRANT_TYPE
        )
    }

    fun refreshToken(refreshToken: String) {
        repository.refreshToken(
            accessToken,
            refreshToken,
            OAUTH_CLIENT_ID,
            OAUTH_CLIENT_SECRET,
            OAUTH_GRANT_TYPE
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