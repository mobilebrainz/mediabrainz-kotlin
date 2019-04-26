package app.mediabrainz.domain.repository

import androidx.annotation.StringRes
import androidx.lifecycle.MutableLiveData
import app.mediabrainz.domain.OAuthManager
import app.mediabrainz.ui.R
import kotlinx.coroutines.*
import retrofit2.HttpException
import retrofit2.Response


abstract class BaseApiRepository {

    private val extraTimeout = 250L
    private val job = Job()
    private val scope = CoroutineScope(Dispatchers.IO + job)
    //private val scope = CoroutineScope(Dispatchers.Default + job)

    open fun cancelJob() {
        job.cancel()
    }

    protected fun <IN, OUT> call(
        mutableLiveData: MutableLiveData<Resource<OUT>>,
        deferred: () -> Deferred<Response<IN>>,
        map: IN.() -> OUT,
        authorize: Boolean
    ) {
        setLoading(mutableLiveData)
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
                        postError(mutableLiveData, R.string.authorization_error)
                        return@launch
                    }
                }
                var httpError = true
                val response = deferred.invoke().await()
                when (response.code()) {
                    200 -> {
                        response.body()?.let {
                            httpError = false
                            postSuccess(mutableLiveData, map.invoke(it))
                        }
                    }
                    503 -> {
                        val retryAfter = response.headers().get("retry-after")
                        if (!retryAfter.isNullOrEmpty()) {
                            httpError = false
                            delay(retryAfter.toLong() * 1000 + extraTimeout)
                            launch(mutableLiveData, deferred, map, authorize)
                        }
                    }
                    401 -> {
                        httpError = false
                        postError(mutableLiveData, R.string.auth_access_error)
                    }
                }
                if (httpError) {
                    postError(mutableLiveData, R.string.http_error)
                }
            } catch (e: HttpException) {
                postError(mutableLiveData, R.string.http_error)
            } catch (e: Throwable) {
                postError(mutableLiveData, R.string.app_error)
            }
        }
    }

    private fun <OUT> setLoading(mutableLiveData: MutableLiveData<Resource<OUT>>) {
        mutableLiveData.value = Resource.loading()
    }

    private fun <OUT> postSuccess(mutableLiveData: MutableLiveData<Resource<OUT>>, data: OUT) {
        mutableLiveData.postValue(Resource.success(data))
    }

    private fun <OUT> postError(mutableLiveData: MutableLiveData<Resource<OUT>>, @StringRes messageResId: Int) {
        mutableLiveData.postValue(Resource.error(messageResId))
    }

}