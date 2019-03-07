package app.mediabrainz.api.core

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit


object MusicBrainzServiceGenerator {

    val API_BASE_URL = "http://musicbrainz.org/ws/2/";
    val AUTH_URL = "https://test.musicbrainz.org/oauth2/authorize"
    val CLIENT_ID = "X2rIt5DgKkd0W6XsSjBqWg"
    val CLIENT_SECRET = "FA5Fa8zEJQs43Jpig_rtgw"
    val OAUTH_REDIRECT_URI = "org.metabrainz.mobile://oauth"
    val TIMEOUT = 20000L

    val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    val headerInterceptor = HeaderInterceptor()

    val httpClientBuilder = OkHttpClient.Builder()
        .callTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
        .connectTimeout(TIMEOUT, TimeUnit.MILLISECONDS)

    val builder = Retrofit.Builder()
        .baseUrl(API_BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())

    var retrofit = builder.build()

    fun <S> createService(service: Class<S>): S {
        addInterceptors(loggingInterceptor)
        addInterceptors(headerInterceptor)

        return retrofit.create(service)
    }

    private fun addInterceptors(interceptor: Interceptor) {
        if (!httpClientBuilder.interceptors().contains(interceptor)) {
            httpClientBuilder.addInterceptor(interceptor)
            builder.client(httpClientBuilder.build())
            retrofit = builder.build()
        }
    }

}