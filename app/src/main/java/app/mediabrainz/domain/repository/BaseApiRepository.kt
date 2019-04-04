package app.mediabrainz.domain.repository

import androidx.lifecycle.MutableLiveData
import app.mediabrainz.domain.OAuthManager
import kotlinx.coroutines.*
import retrofit2.HttpException
import retrofit2.Response


abstract class BaseApiRepository {

    protected val extraTimeout = 250L
    protected val job = Job()
    protected val scope = CoroutineScope(Dispatchers.IO + job)
    //private val scope = CoroutineScope(Dispatchers.Default + job)

    open fun cancelJob() {
        job.cancel()
    }

    protected fun <IN, OUT> call(
        mutableLiveData: MutableLiveData<Resource<OUT>>,
        deferred: () -> Deferred<Response<IN>>,
        map: IN.() -> OUT,
        authorize: Boolean = false
    ) {
        mutableLiveData.value = Resource.loading()
        launch(mutableLiveData, deferred, map, authorize)
    }

    private fun <IN, OUT> launch(
        mutableLiveData: MutableLiveData<Resource<OUT>>,
        deferred: () -> Deferred<Response<IN>>,
        map: IN.() -> OUT,
        authorize: Boolean
    ) {
        scope.launch {
            try {
                if (authorize) {
                    OAuthManager.refreshToken()
                    if (OAuthManager.isError) {
                        mutableLiveData.postValue(Resource.error("Authorization Error!"))
                        return@launch
                    }
                }
                var httpError = false
                val response = deferred.invoke().await()
                when {
                    response.code() == 200 -> {
                        val body = response.body()
                        if (body != null) {
                            mutableLiveData.postValue(Resource.success(map.invoke(body)))
                        } else {
                            httpError = true
                        }
                    }
                    response.code() == 503 -> {
                        val retryAfter = response.headers().get("retry-after")
                        if (retryAfter != null && retryAfter != "") {
                            delay(retryAfter.toLong() * 1000 + extraTimeout)
                            launch(mutableLiveData, deferred, map, authorize)
                        } else {
                            httpError = true
                        }
                    }
                    else -> {
                        httpError = true
                    }
                }
                if (httpError) {
                    mutableLiveData.postValue(Resource.error("Http Error!"))
                }
            } catch (e: HttpException) {
                mutableLiveData.postValue(Resource.error("Http Error!"))
            } catch (e: Throwable) {
                mutableLiveData.postValue(Resource.error("Application Error!"))
            }
        }
    }

}