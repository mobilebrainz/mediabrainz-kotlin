package app.mediabrainz.domain.datasource

import androidx.paging.PageKeyedDataSource
import app.mediabrainz.api.response.BaseSearchResponse
import app.mediabrainz.domain.mapper.PageMapper
import kotlinx.coroutines.*
import retrofit2.HttpException
import retrofit2.Response


abstract class BaseSearchDataSource<IN, OUT, T : BaseSearchResponse<IN>> : PageKeyedDataSource<Int, OUT>() {

    companion object {
        const val SEARCH_LIMIT = 25
    }

    private val extraTimeout = 250L
    private val job = Job()
    private val scope = CoroutineScope(Dispatchers.IO + job)
    //private val scope = CoroutineScope(Dispatchers.Default + job)

    protected abstract fun getRequest(offset: Int): Deferred<Response<T>>

    protected abstract fun getMap(): (IN) -> OUT

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, OUT>) {
        call(callback, null, 0)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, OUT>) {
        call(null, callback, params.key)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, OUT>) {
        //call(null, callback, params.key)
    }

    private fun call(
        loadInitialCallback: LoadInitialCallback<Int, OUT>?,
        loadCallback: LoadCallback<Int, OUT>?,
        offset: Int = 0
    ) {
        scope.launch {
            try {
                var httpError = false
                val response = getRequest(offset).await()
                when {
                    response.code() == 200 -> {
                        val body = response.body()
                        if (body != null) {
                            val entities = PageMapper(getMap()).mapTo(body)
                            val nextOffset = entities.offset + SEARCH_LIMIT
                            val nextPageKey = if (entities.count > nextOffset) nextOffset else null

                            if (loadInitialCallback != null) {
                                loadInitialCallback.onResult(entities.items, null, nextPageKey)
                            } else {
                                loadCallback?.onResult(entities.items, nextPageKey)
                            }
                        } else {
                            httpError = true
                        }
                    }
                    response.code() == 503 -> {
                        val retryAfter = response.headers().get("retry-after")
                        if (retryAfter != null && retryAfter != "") {
                            delay(retryAfter.toLong() * 1000 + extraTimeout)
                            call(loadInitialCallback, loadCallback, offset)
                        } else {
                            httpError = true
                        }
                    }
                    else -> {
                        httpError = true
                    }
                }
                if (httpError) {
                    //mutableLiveData.postValue(Resource.error("Http Error!"))
                }
            } catch (e: HttpException) {
                //mutableLiveData.postValue(Resource.error("Http Error!"))
            } catch (e: Throwable) {
                //mutableLiveData.postValue(Resource.error("Application Error!"))
            }
        }
    }

    fun cancelJob() {
        job.cancel()
    }
}