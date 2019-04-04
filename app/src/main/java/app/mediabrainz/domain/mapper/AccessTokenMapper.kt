package app.mediabrainz.domain.mapper

import app.mediabrainz.api.oauth.AccessTokenResponse
import app.mediabrainz.domain.model.AccessToken


class AccessTokenMapper {

    fun mapTo(response: AccessTokenResponse) = with(response) {
        val token = AccessToken(
            accessToken,
            expiresIn * 1000 + System.currentTimeMillis(),
            refreshToken
        )
        token
    }

}