package app.mediabrainz.domain.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import app.mediabrainz.api.ApiRequestProvider
import app.mediabrainz.api.response.TagResponse
import app.mediabrainz.api.response.TagSearchResponse
import app.mediabrainz.domain.mapper.TagMapper
import app.mediabrainz.domain.model.Tag
import app.mediabrainz.domain.parenthesesString


class TagSearchDataSource(val query: String) :
    BaseSearchDataSource<TagResponse, Tag, TagSearchResponse>() {

    override fun request(loadSize: Int, offset: Int) = ApiRequestProvider.createTagSearchRequest()
        .search(parenthesesString(query), loadSize, offset)

    override fun map() = TagMapper()::mapTo

    class Factory(val query: String) : DataSource.Factory<Int, Tag>() {
        val dataSourceLiveData = MutableLiveData<DataSourceInterface>()

        override fun create(): PageKeyedDataSource<Int, Tag> {
            val dataSource = TagSearchDataSource(query)
            dataSourceLiveData.postValue(dataSource)
            return dataSource
        }
    }

}