package app.mediabrainz.domain.repository

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*
import retrofit2.HttpException
import retrofit2.Response


abstract class BaseApiRepository<IN, OUT> {

    private val extraTimeout = 250L

    val mutableLiveData: MutableLiveData<Resource<OUT>> = MutableLiveData()

    private val job = Job()
    private val scope = CoroutineScope(Dispatchers.IO + job)
    //private val scope = CoroutineScope(Dispatchers.Default + job)

    protected fun call(
        deferred: Deferred<Response<IN>>,
        action503: () -> Unit,
        map: IN.() -> OUT
    ) {
        scope.launch {
            try {
                var httpError = false
                val response = deferred.await()
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
                            action503.invoke()
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

    fun cancelJob() {
        job.cancel()
    }

}