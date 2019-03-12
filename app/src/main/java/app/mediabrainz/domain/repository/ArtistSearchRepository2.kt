package app.mediabrainz.domain.repository

import androidx.lifecycle.MutableLiveData
import app.mediabrainz.api.response.ArtistSearchResponse
import app.mediabrainz.api.service.ApiServiceProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException


class ArtistSearchRepository2 {

    private val retryTimeout503 = 250L
    private var retryLimit503 = 4
    private var retryCount503 = 0

    private val timeout503 = 1000L
    private val limit503 = 10
    private var count503 = 0

    fun search(artist: String, limit: Int, offset: Int): MutableLiveData<Resource<ArtistSearchResponse?>> {
        val mutableLiveData = MutableLiveData<Resource<ArtistSearchResponse?>>()
        mutableLiveData.value = Resource.loading(null)
        search503(mutableLiveData, artist, limit, offset)
        return mutableLiveData
    }

    fun search503(
        mutableLiveData: MutableLiveData<Resource<ArtistSearchResponse?>>,
        artist: String,
        limit: Int,
        offset: Int
    ) {
        val request = ApiServiceProvider.createArtistSearchService().search(artist, limit, offset)
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = request.await()
                when {
                    response.code() == 200 -> mutableLiveData.postValue(Resource.success(response.body()))
                    response.code() == 503 -> {
                        val retrySt = response.headers().get("retry-after")
                        if (retrySt != null && retrySt != "") {
                            if (retryCount503 < retryLimit503) {
                                retryCount503++
                                val retryInt = retrySt.toLong() * 1000 + retryTimeout503
                                Thread.sleep(retryInt)
                                search503(mutableLiveData, artist, limit, offset)
                            } else {
                                mutableLiveData.postValue(Resource.error("Http Error!", null))
                            }
                        } else {
                            if (count503 < limit503) {
                                count503++
                                Thread.sleep(timeout503)
                                search503(mutableLiveData, artist, limit, offset)
                            } else {
                                mutableLiveData.postValue(Resource.error("Http Error!", null))
                            }
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