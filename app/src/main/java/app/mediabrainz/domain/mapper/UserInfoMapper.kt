package app.mediabrainz.domain.mapper

import app.mediabrainz.api.oauth.UserInfoResponse
import app.mediabrainz.domain.model.UserInfo


class UserInfoMapper {

    fun mapTo(response: UserInfoResponse) = with(response) {
        val userInfo = UserInfo()
        userInfo
    }

}