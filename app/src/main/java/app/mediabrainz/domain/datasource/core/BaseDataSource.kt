package app.mediabrainz.domain.datasource.core

import androidx.annotation.StringRes
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import app.mediabrainz.api.response.BaseItemsResponse
import app.mediabrainz.domain.OAuthManager
import app.mediabrainz.domain.mapper.PageMapper
import app.mediabrainz.ui.R
import kotlinx.coroutines.*
import retrofit2.HttpException
import retrofit2.Response


abstract class BaseDataSource<IN, OUT, T : BaseItemsResponse<IN>> :
    PageKeyedDataSource<Int, OUT>(),
    DataSourceInterface {

    private val initialLoadState = MutableLiveData<NetworkState>()
    override fun initialLoadState() = initialLoadState

    private val afterLoadState = MutableLiveData<NetworkState>()
    override fun afterLoadState() = afterLoadState

    private var isInitialLoad: Boolean = false
    private val extraTimeout = 250L
    private val job = Job()
    private val scope = CoroutineScope(Dispatchers.IO + job)
    //private val scope = CoroutineScope(Dispatchers.Default + job)
    private var retryAction: (() -> Unit)? = null

    protected abstract fun request(loadSize: Int, offset: Int): Deferred<Response<T>>

    protected abstract fun map(): (IN) -> OUT

    open fun isAuthorized() = false

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, OUT>) {
        isInitialLoad = true
        postLoading()
        callInitial(callback, params.requestedLoadSize)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, OUT>) {
        isInitialLoad = false
        postLoading()
        callAfter(callback, params.requestedLoadSize, params.key)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, OUT>) {
        //isInitialLoad = false
        //postLoading()
        //callAfter(callback, params.requestedLoadSize, params.key)
    }

    private fun callInitial(loadInitialCallback: LoadInitialCallback<Int, OUT>?, loadSize: Int) =
        call(loadInitialCallback, null, loadSize, 0)


    private fun callAfter(callback: LoadCallback<Int, OUT>, loadSize: Int, offset: Int) =
        call(null, callback, loadSize, offset)


    override fun retry() {
        retryAction?.let {
            postLoading()
            it.invoke()
        }
    }

    override fun refresh() {
        //todo: перезагружает сразу все ранее загруженные страницы, а надо только первые как при начальной загрузке?
        invalidate()
    }

    private fun call(
        loadInitialCallback: LoadInitialCallback<Int, OUT>?,
        loadCallback: LoadCallback<Int, OUT>?,
        loadSize: Int,
        offset: Int
    ) {
        scope.launch {
            try {
                if (isAuthorized()) {
                    OAuthManager.refreshToken()
                    if (OAuthManager.isError) {
                        postError(R.string.authorization_error)
                        return@launch
                    }
                }
                var httpError = true
                val response = request(loadSize, offset).await()
                when (response.code()) {
                    200 -> {
                        response.body()?.let {
                            httpError = false
                            retryAction = null
                            val entities = PageMapper(map()).mapTo(it)
                            val nextOffset = entities.offset + loadSize
                            val nextPageKey = if (entities.count > nextOffset) nextOffset else null

                            if (loadInitialCallback != null) {
                                loadInitialCallback.onResult(entities.items, null, nextPageKey)
                            } else {
                                loadCallback?.onResult(entities.items, nextPageKey)
                            }
                            postSuccess()
                        }
                    }
                    503 -> {
                        val retryAfter = response.headers().get("retry-after")
                        if (!retryAfter.isNullOrEmpty()) {
                            httpError = false
                            retryAction = null
                            delay(retryAfter.toLong() * 1000 + extraTimeout)
                            call(loadInitialCallback, loadCallback, loadSize, offset)
                        }
                    }
                }
                if (httpError) {
                    retryAction = { call(loadInitialCallback, loadCallback, loadSize, offset) }
                    postError(R.string.http_error)
                }
            } catch (e: HttpException) {
                retryAction = { call(loadInitialCallback, loadCallback, loadSize, offset) }
                postError(R.string.http_error)
            } catch (e: Throwable) {
                retryAction = { call(loadInitialCallback, loadCallback, loadSize, offset) }
                postError(R.string.app_error)
            }
        }
    }

    private fun postLoading() {
        if (isInitialLoad) {
            initialLoadState.postValue(NetworkState.loading())
        } else {
            afterLoadState.postValue(NetworkState.loading())
        }
    }

    private fun postSuccess() {
        if (isInitialLoad) {
            initialLoadState.postValue(NetworkState.success())
        } else {
            afterLoadState.postValue(NetworkState.success())
        }
    }

    private fun postError(@StringRes messageResId: Int) {
        if (isInitialLoad) {
            initialLoadState.postValue(NetworkState.error(messageResId))
        } else {
            afterLoadState.postValue(NetworkState.error(messageResId))
        }
    }

    override fun cancelJob() {
        job.cancel()
    }
}