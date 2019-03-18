package app.mediabrainz.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import app.mediabrainz.domain.datasource.DataSourceInterface


abstract class BaseDataSourceViewModel : ViewModel() {

    lateinit var dataSourceLiveData: MutableLiveData<DataSourceInterface>

    fun getAfterLoadState() =
        Transformations.switchMap(dataSourceLiveData, DataSourceInterface::afterLoadState)

    fun getInitialLoadState() =
        Transformations.switchMap(dataSourceLiveData, DataSourceInterface::initialLoadState)

    fun refresh() {
        if (::dataSourceLiveData.isInitialized) {
            dataSourceLiveData.value?.refresh()
        }
    }

    fun retry() {
        if (::dataSourceLiveData.isInitialized) {
            dataSourceLiveData.value?.retry()
        }
    }

    override fun onCleared() {
        super.onCleared()
        if (::dataSourceLiveData.isInitialized) {
            dataSourceLiveData.value?.cancelJob()
        }
    }

}