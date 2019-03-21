package app.mediabrainz.domain.datasource.core

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource


abstract class DataSourceFactory<Value> : DataSource.Factory<Int, Value>() {

    val dataSourceLiveData = MutableLiveData<DataSourceInterface>()

    fun setDataSource(dataSource: DataSourceInterface) {
        dataSourceLiveData.postValue(dataSource)
    }

}