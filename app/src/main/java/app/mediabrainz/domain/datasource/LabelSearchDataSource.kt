package app.mediabrainz.domain.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import app.mediabrainz.api.ApiRequestProvider
import app.mediabrainz.api.response.LabelResponse
import app.mediabrainz.api.response.LabelSearchResponse
import app.mediabrainz.domain.mapper.LabelMapper
import app.mediabrainz.domain.model.Label
import app.mediabrainz.domain.parenthesesString


class LabelSearchDataSource(val query: String) :
    BaseSearchDataSource<LabelResponse, Label, LabelSearchResponse>() {

    override fun request(loadSize: Int, offset: Int) = ApiRequestProvider.createLabelSearchRequest()
        .search(parenthesesString(query), loadSize, offset)

    override fun map() = LabelMapper()::mapTo

    class Factory(val query: String) : DataSource.Factory<Int, Label>() {
        val dataSourceLiveData = MutableLiveData<DataSourceInterface>()

        override fun create(): PageKeyedDataSource<Int, Label> {
            val dataSource = LabelSearchDataSource(query)
            dataSourceLiveData.postValue(dataSource)
            return dataSource
        }
    }

}