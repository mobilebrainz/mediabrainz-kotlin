package app.mediabrainz.domain.model


class AccessToken(val token: String, val expiresIn: Long, val refreshToken: String)


