package app.mediabrainz.domain.repository

import androidx.lifecycle.MutableLiveData
import app.mediabrainz.api.ApiRequestProvider
import app.mediabrainz.api.core.Config
import app.mediabrainz.domain.mapper.AccessTokenMapper
import app.mediabrainz.domain.mapper.TokenInfoMapper
import app.mediabrainz.domain.mapper.UserInfoMapper
import app.mediabrainz.domain.model.AccessToken
import app.mediabrainz.domain.model.TokenInfo
import app.mediabrainz.domain.model.UserInfo


class OAuthRepository : BaseApiRepository() {

    fun requestToken(
        mutableLiveData: MutableLiveData<Resource<AccessToken>>,
        code: String, clientId: String, clientSecret: String,
        redirectUri: String, grantType: String
    ) {
        call(mutableLiveData,
            {
                ApiRequestProvider.createOAuthRequest()
                    .requestToken(code, clientId, clientSecret, redirectUri, grantType)
            },
            { AccessTokenMapper().mapTo(this) })

    }

    fun refreshToken(
        mutableLiveData: MutableLiveData<Resource<AccessToken>>,
        refreshToken: String, clientId: String, clientSecret: String, grantType: String
    ) {
        call(mutableLiveData,
            {
                ApiRequestProvider.createOAuthRequest()
                    .refreshToken(refreshToken, clientId, clientSecret, grantType)
            },
            { AccessTokenMapper().mapTo(this) })
    }

    fun getUserInfo(mutableLiveData: MutableLiveData<Resource<UserInfo>>, accessToken: String) {
        call(mutableLiveData,
            { ApiRequestProvider.createOAuthRequest().getUserInfo(accessToken) },
            { UserInfoMapper().mapTo(this) })

    }

    fun getTokenInfo(mutableLiveData: MutableLiveData<Resource<TokenInfo>>, accessToken: String) {
        call(mutableLiveData,
            { ApiRequestProvider.createOAuthRequest().getTokenInfo(accessToken) },
            { TokenInfoMapper().mapTo(this) })
    }

}