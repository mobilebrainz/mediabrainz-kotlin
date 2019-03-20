package app.mediabrainz.domain.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import app.mediabrainz.api.ApiRequestProvider
import app.mediabrainz.api.response.RecordingResponse
import app.mediabrainz.api.response.RecordingSearchResponse
import app.mediabrainz.api.searchrequest.RecordingSearchField
import app.mediabrainz.domain.mapper.RecordingMapper
import app.mediabrainz.domain.model.Recording
import app.mediabrainz.domain.parenthesesString


class RecordingSearchDataSource(
    val artist: String,
    val release: String,
    val recording: String
) : BaseSearchDataSource<RecordingResponse, Recording, RecordingSearchResponse>() {

    override fun request(loadSize: Int, offset: Int) = ApiRequestProvider.createRecordingSearchRequest()
        .add(RecordingSearchField.ARTIST, parenthesesString(artist))
        .add(RecordingSearchField.RELEASE, parenthesesString(release))
        .add(RecordingSearchField.RECORDING, parenthesesString(recording))
        .search(loadSize, offset)

    override fun map() = RecordingMapper()::mapTo

    class Factory(
        val artist: String,
        val release: String,
        val recording: String
    ) : DataSource.Factory<Int, Recording>() {

        val dataSourceLiveData = MutableLiveData<DataSourceInterface>()

        override fun create(): PageKeyedDataSource<Int, Recording> {
            val dataSource = RecordingSearchDataSource(artist, release, recording)
            dataSourceLiveData.postValue(dataSource)
            return dataSource
        }
    }

}