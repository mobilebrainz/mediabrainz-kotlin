package app.mediabrainz.domain.datasource.core

import androidx.lifecycle.MutableLiveData

interface DataSourceInterface {

    fun retry()

    fun refresh()

    fun cancelJob()

    fun initialLoadState(): MutableLiveData<NetworkState>

    fun afterLoadState(): MutableLiveData<NetworkState>

}