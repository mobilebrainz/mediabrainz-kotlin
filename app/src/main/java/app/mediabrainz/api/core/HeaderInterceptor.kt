package app.mediabrainz.api.core

import okhttp3.Interceptor
import okhttp3.Response


class HeaderInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val request = original.newBuilder()
            .header("User-agent", "MusicBrainzAndroid/Test (kartikohri13@gmail.com)")
            .addHeader("Accept", "application/json")
            .build()
        return chain.proceed(request)
    }

}