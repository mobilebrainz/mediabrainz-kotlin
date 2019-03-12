package app.mediabrainz.domain.repository

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import retrofit2.Response


abstract class BaseRepository {

    private val retryTimeout503 = 250L
    private val retryLimit503 = 4
    private var retryCount503 = 0

    private val timeout503 = 1000L
    private val limit503 = 10
    private var count503 = 0

    private fun cancelTimers() {
        retryCount503 = 0
        count503 = 0
    }

    fun <IN, OUT> call(
        mutableLiveData: MutableLiveData<Resource<OUT>>,
        deferred: Deferred<Response<IN>>,
        action503: () -> Unit,
        map: IN.() -> OUT
    ) {
        // todo: Dispatchers? GlobalScope.launch(Dispatchers.Main)
        CoroutineScope(Dispatchers.IO).launch {
            try {
                var httpError = false
                val response = deferred.await()
                when {
                    response.code() == 200 -> {
                        cancelTimers()
                        if (response.body() != null) {
                            mutableLiveData.postValue(Resource.success(map.invoke(response.body()!!)))
                        } else {
                            httpError = true
                        }
                    }
                    response.code() == 503 -> {
                        val retryStr = response.headers().get("retry-after")
                        if (retryStr != null && retryStr != "") {
                            if (retryCount503 < retryLimit503) {
                                retryCount503++
                                val retryInt = retryStr.toLong() * 1000 + retryTimeout503
                                Thread.sleep(retryInt)
                                action503.invoke()
                            } else {
                                httpError = true
                            }
                        } else {
                            if (count503 < limit503) {
                                count503++
                                Thread.sleep(timeout503)
                                action503.invoke()
                            } else {
                                httpError = true
                            }
                        }
                    }
                    else -> {
                        httpError = true
                    }
                }
                if (httpError) {
                    cancelTimers()
                    mutableLiveData.postValue(Resource.error("Http Error!", null))
                }
            } catch (e: HttpException) {
                cancelTimers()
                mutableLiveData.postValue(Resource.error("Http Error!", null))
            } catch (e: Throwable) {
                cancelTimers()
                mutableLiveData.postValue(Resource.error("Application Error!", null))
            }
        }
    }

}