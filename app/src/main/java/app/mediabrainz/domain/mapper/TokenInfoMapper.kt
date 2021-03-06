package app.mediabrainz.domain.mapper

import app.mediabrainz.api.oauth.TokenInfoResponse
import app.mediabrainz.domain.model.TokenInfo


class TokenInfoMapper {

    fun mapTo(response: TokenInfoResponse) = with(response) {
        val tokenInfo = TokenInfo()
        tokenInfo
    }

}