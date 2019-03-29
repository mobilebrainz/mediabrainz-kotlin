package app.mediabrainz.api.oauth

import com.squareup.moshi.Json


data class AccessTokenResponse (
    @field:Json(name = "access_token") val accessToken: String,
    @field:Json(name = "expires_in") val expiresIn: Long,
    @field:Json(name = "token_type") val tokenType: String,
    @field:Json(name = "refresh_token") val refreshToken: String
)

data class TokenInfoResponse (
    @field:Json(name = "error") val error: String?,
    @field:Json(name = "error_description") val errorDescription: String?,
    @field:Json(name = "access_type") val accessType: String?,
    @field:Json(name = "expires_in") val expiresIn: Long?,
    @field:Json(name = "audience") val audience: String?,
    @field:Json(name = "scope") val scope: String?,
    @field:Json(name = "issued_to") val issuedTo: String?,
    @field:Json(name = "token_type") val tokenType: String?
)

data class UserInfoResponse (
    @field:Json(name = "metabrainz_user_id") val userId: Int,
    @field:Json(name = "sub") val username: String,
    @field:Json(name = "profile") val profile: String,
    @field:Json(name = "email_verified") val emailVerified: Boolean?,
    @field:Json(name = "email") val email: String?,
    @field:Json(name = "zoneinfo") val zoneinfo: String?,
    @field:Json(name = "gender") val gender: String?
)

