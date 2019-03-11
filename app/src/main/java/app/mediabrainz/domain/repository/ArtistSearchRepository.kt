package app.mediabrainz.domain.repository

import androidx.lifecycle.MutableLiveData
import app.mediabrainz.api.response.ArtistSearchResponse
import app.mediabrainz.api.service.ApiServiceProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import retrofit2.Response


class ArtistSearchRepository {

    private val timeout503 = 1000L
    private val limit503 = 10
    private var count503 = 0

    fun search(
        mutableLiveData: MutableLiveData<Resource<ArtistSearchResponse?>>,
        artist: String,
        limit: Int,
        offset: Int
    ) {
        mutableLiveData.value = Resource.loading(null)
        val request = ApiServiceProvider.createArtistSearchService().search(artist, limit, offset)
        search(mutableLiveData, request)
    }

    fun search(
        mutableLiveData: MutableLiveData<Resource<ArtistSearchResponse?>>,
        request: Deferred<Response<ArtistSearchResponse>>
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = request.await()
                when {
                    response.code() == 200 -> mutableLiveData.postValue(Resource.success(response.body()))
                    response.code() == 503 -> {
                        if (count503 < limit503) {
                            count503++
                            Thread.sleep(timeout503)
                            search(mutableLiveData, request)
                        } else {
                            mutableLiveData.postValue(Resource.error("Http Error!", null))
                        }
                    }
                    else -> mutableLiveData.postValue(Resource.error("Http Error!", null))
                }
            } catch (e: HttpException) {
                mutableLiveData.postValue(Resource.error("Http Error!", null))
            } catch (e: Throwable) {
                mutableLiveData.postValue(Resource.error("Application Error!", null))
            }
        }
    }

}