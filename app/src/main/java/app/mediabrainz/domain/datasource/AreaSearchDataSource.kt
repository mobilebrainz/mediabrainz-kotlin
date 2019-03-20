package app.mediabrainz.domain.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import app.mediabrainz.api.ApiRequestProvider
import app.mediabrainz.api.response.AreaResponse
import app.mediabrainz.api.response.AreaSearchResponse
import app.mediabrainz.domain.mapper.AreaMapper
import app.mediabrainz.domain.model.Area
import app.mediabrainz.domain.parenthesesString


class AreaSearchDataSource(val query: String) :
    BaseSearchDataSource<AreaResponse, Area, AreaSearchResponse>() {

    override fun request(loadSize: Int, offset: Int) = ApiRequestProvider.createAreaSearchRequest()
        .search(parenthesesString(query), loadSize, offset)

    override fun map() = AreaMapper()::mapTo

    class Factory(val query: String) : DataSource.Factory<Int, Area>() {
        val dataSourceLiveData = MutableLiveData<DataSourceInterface>()

        override fun create(): PageKeyedDataSource<Int, Area> {
            val dataSource = AreaSearchDataSource(query)
            dataSourceLiveData.postValue(dataSource)
            return dataSource
        }
    }

}