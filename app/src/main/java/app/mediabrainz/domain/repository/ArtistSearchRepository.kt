package app.mediabrainz.domain.repository

import androidx.lifecycle.MutableLiveData
import app.mediabrainz.api.response.ArtistSearchResponse
import app.mediabrainz.api.service.ApiServiceProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException


class ArtistSearchRepository {

    fun search(artist: String, limit: Int, offset: Int): MutableLiveData<Resource<ArtistSearchResponse?>> {
        val mutableLiveData = MutableLiveData<Resource<ArtistSearchResponse?>>()
        mutableLiveData.value = Resource.loading(null)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = ApiServiceProvider.createArtistSearchService()
                    .search("Riverside", limit, offset)
                    .await()
                when {
                    response.code() == 200 -> mutableLiveData.postValue(Resource.success(response.body()))
                    response.code() == 503 -> {

                    }
                    else -> mutableLiveData.postValue(Resource.error("Http Error!", null))
                }
                // Do something with the response.body()
            } catch (e: HttpException) {
                mutableLiveData.postValue(Resource.error("Http Error!", null))
            } catch (e: Throwable) {
                mutableLiveData.postValue(Resource.error("Application Error!", null))
            }
        }
        return mutableLiveData
    }
}