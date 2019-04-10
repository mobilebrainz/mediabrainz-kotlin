package app.mediabrainz.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import app.mediabrainz.domain.datasource.core.NetworkState.Status.ERROR
import app.mediabrainz.domain.datasource.core.NetworkState.Status.LOADING
import app.mediabrainz.ui.R
import app.mediabrainz.ui.adapter.AlbumSearchAdapter
import app.mediabrainz.ui.adapter.ArtistSearchAdapter
import app.mediabrainz.ui.adapter.RecordingSearchAdapter
import app.mediabrainz.ui.viewmodel.BaseDataSourceViewModel
import app.mediabrainz.ui.viewmodel.searchDataSource.PagedArtistSearchViewModel
import app.mediabrainz.ui.viewmodel.searchDataSource.PagedRecordingSearchViewModel
import app.mediabrainz.ui.viewmodel.searchDataSource.PagedReleaseGroupSearchViewModel


abstract class BaseDataSourceFragment : BaseFragment() {

    protected var isLoading: Boolean = false

    protected lateinit var recyclerView: RecyclerView
    protected lateinit var swipeRefreshLayout: SwipeRefreshLayout
    protected lateinit var viewModel: BaseDataSourceViewModel<*>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.paged_recycler_fragment, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout)
        initRecycler()
        return view
    }

    protected fun initRecycler() {
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.isNestedScrollingEnabled = true
        //recyclerView.setItemViewCacheSize(10)
        recyclerView.setHasFixedSize(true)
    }

    protected fun initSwipeToRefresh() {
        viewModel.getInitialLoadState().observe(this, Observer {
            isLoading = it.status == LOADING
            swipeRefreshLayout.isRefreshing = isLoading
            showError(it.status == ERROR, it.messageResId)
        })

        viewModel.getAfterLoadState().observe(this, Observer {
            isLoading = it.status == LOADING
            swipeRefreshLayout.isRefreshing = isLoading
            showError(it.status == ERROR, it.messageResId)
        })

        swipeRefreshLayout.setOnRefreshListener {
            if (!isLoading) {
                viewModel.refresh()
                recyclerView.scrollToPosition(0)
            }
        }
    }

    protected fun showError(show: Boolean, @StringRes messageResId: Int) {
        if (show) {
            showErrorSnackbar(messageResId, R.string.retry, View.OnClickListener { retry() })
        } else {
            dismissErrorSnackbar()
        }
    }

    protected fun retry() {
        viewModel.retry()
    }

}
