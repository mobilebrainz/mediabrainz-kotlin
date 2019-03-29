package app.mediabrainz.domain.model


class AccessToken(
    val accessToken: String,
    val expiresIn: Long,
    val refreshToken: String
)


