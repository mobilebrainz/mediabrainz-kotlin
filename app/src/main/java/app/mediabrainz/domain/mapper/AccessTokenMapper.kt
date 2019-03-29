package app.mediabrainz.domain.mapper

import app.mediabrainz.api.oauth.AccessTokenResponse
import app.mediabrainz.api.response.AreaResponse
import app.mediabrainz.domain.model.AccessToken
import app.mediabrainz.domain.model.Area


class AccessTokenMapper {

    fun mapTo(response: AccessTokenResponse) = with(response) {
        val token = AccessToken(accessToken, expiresIn, refreshToken)
        token
    }

}