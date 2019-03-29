package app.mediabrainz.api.retrofit

import app.mediabrainz.api.core.Config.OAUTH_TOKEN_INFO_QUERY
import app.mediabrainz.api.core.Config.OAUTH_TOKEN_QUERY
import app.mediabrainz.api.core.Config.OAUTH_USER_INFO_QUERY
import app.mediabrainz.api.oauth.AccessTokenResponse
import app.mediabrainz.api.oauth.TokenInfoResponse
import app.mediabrainz.api.oauth.UserInfoResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.*


interface OAuthRequestService {

    @GET(OAUTH_USER_INFO_QUERY)
    fun getUserInfo(@Query("access_token") accessToken: String): Deferred<Response<UserInfoResponse>>

    @GET(OAUTH_TOKEN_INFO_QUERY)
    fun getTokenInfo(@Query("access_token") accessToken: String): Deferred<Response<TokenInfoResponse>>

    @FormUrlEncoded
    @POST(OAUTH_TOKEN_QUERY)
    fun requestToken(
        @Field("code") code: String,
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String, //Is not relevant for Android application?
        @Field("redirect_uri") redirectUri: String,
        @Field("grant_type") grantType: String
    ): Deferred<Response<AccessTokenResponse>>

    @FormUrlEncoded
    @POST(OAUTH_TOKEN_QUERY)
    fun refreshToken(
        @Field("refresh_token") refreshToken: String,
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String, //Is not relevant for Android application?
        @Field("grant_type") grantType: String
    ): Deferred<Response<AccessTokenResponse>>


    ////////////////// authorization via site ///////////////////
    /*
    @GET(OAUTH_AUTHORIZE_QUERY)
    fun authorize(@QueryMap params: Map<String, String> ): Deferred<Response<ResponseBody>>

    @FormUrlEncoded
    @POST(OAUTH_AUTHORIZE_QUERY)
    fun login(
        @QueryMap params: Map<String, String>,
        @Header("Cookie") cookie: String,
        @Field("username") username: String,
        @Field("password") password: String
    ): Deferred<Response<ResponseBody>>

    @FormUrlEncoded
    @POST(OAUTH_AUTHORIZE_QUERY)
    fun allowAccess(
        @QueryMap params: Map<String, String>,
        @Header("Cookie") cookie: String,
        @Field("confirm.submit") confirm: Int
    ): Deferred<Response<ResponseBody>>
    */

}