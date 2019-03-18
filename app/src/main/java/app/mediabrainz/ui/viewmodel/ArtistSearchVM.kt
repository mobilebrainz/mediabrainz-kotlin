package app.mediabrainz.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import app.mediabrainz.domain.datasource.ArtistSearchDataSource
import app.mediabrainz.domain.model.Artist


class ArtistSearchVM : ViewModel() {

    private val initialLoadSize = 25
    private val loadSize = 25

    lateinit var pagedItems: LiveData<PagedList<Artist>>
    lateinit var dataSource: MutableLiveData<ArtistSearchDataSource>

    fun search(query: String) {
        val factory = ArtistSearchDataSource.Factory(query)
        val config = PagedList.Config.Builder()
            .setInitialLoadSizeHint(initialLoadSize)
            .setPageSize(loadSize)
            .setEnablePlaceholders(false)
            .build()

        pagedItems = LivePagedListBuilder<Int, Artist>(factory, config).build()
        dataSource = factory.dataSourceLiveData
    }

    override fun onCleared() {
        super.onCleared()
        if (::dataSource.isInitialized) {
            dataSource.value?.cancelJob()
        }
    }

}