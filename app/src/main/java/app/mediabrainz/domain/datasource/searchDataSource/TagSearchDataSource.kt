package app.mediabrainz.domain.datasource.searchDataSource

import androidx.paging.PageKeyedDataSource
import app.mediabrainz.api.ApiRequestProvider
import app.mediabrainz.api.response.TagResponse
import app.mediabrainz.api.response.TagSearchResponse
import app.mediabrainz.domain.datasource.core.BaseDataSource
import app.mediabrainz.domain.datasource.core.DataSourceFactory
import app.mediabrainz.domain.mapper.TagMapper
import app.mediabrainz.domain.model.Tag
import app.mediabrainz.domain.parenthesesString


class TagSearchDataSource(val query: String) :
    BaseDataSource<TagResponse, Tag, TagSearchResponse>() {

    override fun request(loadSize: Int, offset: Int) = ApiRequestProvider.createTagSearchRequest()
        .search(parenthesesString(query), loadSize, offset)

    override fun map() = TagMapper()::mapTo

    class Factory(val query: String) : DataSourceFactory<Tag>() {
        override fun create(): PageKeyedDataSource<Int, Tag> {
            val dataSource = TagSearchDataSource(query)
            setDataSource(dataSource)
            return dataSource
        }
    }

}