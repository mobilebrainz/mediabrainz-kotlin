package app.mediabrainz.domain.mapper

import app.mediabrainz.api.oauth.AccessTokenResponse
import app.mediabrainz.domain.model.AccessToken


class AccessTokenMapper {

    fun mapTo(response: AccessTokenResponse): AccessToken =
        with(response) {
            AccessToken(
                accessToken,
                expiresIn * 1000 + System.currentTimeMillis(),
                refreshToken
            )
        }
}