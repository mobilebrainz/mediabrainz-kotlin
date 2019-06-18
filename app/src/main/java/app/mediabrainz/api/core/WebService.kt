package app.mediabrainz.api.core

import app.mediabrainz.ui.BuildConfig
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import java.util.concurrent.TimeUnit


object WebService {

    val TIMEOUT = 20000L

    //set lately WebService.userAgentHeader = "app.mediabrainz/0.0.1 (algerd75@mail.ru)"
    var userAgentHeader = "app.mediabrainz/0.0.1 (algerd75@mail.ru)"

    private val headerInterceptor = Interceptor { chain ->
        val request = chain.request()
            .newBuilder()
            .header("User-Agent", userAgentHeader)
            .build()
        chain.proceed(request)
    }

    private val loggingIntercepter = HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
        else HttpLoggingInterceptor.Level.NONE
    }

    private val httpClient: OkHttpClient = OkHttpClient.Builder()
        //.callTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
        //.connectTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
        //.readTimeout(120, TimeUnit.SECONDS)
        //.writeTimeout(90, TimeUnit.SECONDS)
        .addNetworkInterceptor(loggingIntercepter)
        .addInterceptor(headerInterceptor)
        .build()

    fun <T> createJsonRetrofitService(retrofitClass: Class<T>, url: String) =
        Retrofit.Builder().baseUrl(url)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(MoshiConverterFactory.create())
            .client(httpClient).build()
            .create(retrofitClass)

    fun <T> createXMLRetrofitService(retrofitClass: Class<T>, url: String) =
        Retrofit.Builder().baseUrl(url)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .client(httpClient).build()
            .create(retrofitClass)

}