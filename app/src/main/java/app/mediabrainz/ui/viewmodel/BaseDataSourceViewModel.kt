package app.mediabrainz.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import app.mediabrainz.domain.datasource.core.DataSourceFactory
import app.mediabrainz.domain.datasource.core.DataSourceInterface


abstract class BaseDataSourceViewModel<T> : ViewModel() {

    lateinit var dataSourceLiveData: MutableLiveData<DataSourceInterface>
    lateinit var pagedItems: LiveData<PagedList<T>>

    protected fun initPagedItems(config: PagedList.Config, factory: DataSourceFactory<T>) {
        pagedItems = LivePagedListBuilder<Int, T>(factory, config).build()
        dataSourceLiveData = factory.dataSourceLiveData
    }

    protected fun buildPagedListConfig(
        loadSize: Int,
        initialLoadSize: Int = loadSize,
        enablePlaceholders: Boolean = false

    ) = PagedList.Config.Builder()
        .setInitialLoadSizeHint(initialLoadSize)
        .setPageSize(loadSize)
        .setEnablePlaceholders(enablePlaceholders)
        .build()

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