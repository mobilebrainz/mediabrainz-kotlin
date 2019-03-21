package app.mediabrainz.domain.datasource

import androidx.paging.PageKeyedDataSource
import app.mediabrainz.api.ApiRequestProvider
import app.mediabrainz.api.response.WorkResponse
import app.mediabrainz.api.response.WorkSearchResponse
import app.mediabrainz.domain.datasource.core.DataSourceFactory
import app.mediabrainz.domain.mapper.WorkMapper
import app.mediabrainz.domain.model.Work
import app.mediabrainz.domain.parenthesesString


class WorkSearchDataSource(val query: String) :
    BaseSearchDataSource<WorkResponse, Work, WorkSearchResponse>() {

    override fun request(loadSize: Int, offset: Int) = ApiRequestProvider.createWorkSearchRequest()
        .search(parenthesesString(query), loadSize, offset)

    override fun map() = WorkMapper()::mapTo

    class Factory(val query: String) : DataSourceFactory<Work>() {
        override fun create(): PageKeyedDataSource<Int, Work> {
            val dataSource = WorkSearchDataSource(query)
            setDataSource(dataSource)
            return dataSource
        }
    }

}