package app.mediabrainz.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import app.mediabrainz.domain.datasource.core.NetworkState.Status.ERROR
import app.mediabrainz.domain.datasource.core.NetworkState.Status.LOADING
import app.mediabrainz.ui.R
import app.mediabrainz.ui.viewmodel.BaseDataSourceViewModel


abstract class BaseDataSourceFragment : BaseFragment() {

    protected var isLoading: Boolean = false
    protected var isError: Boolean = false
    @StringRes
    protected var errorMessageResId = -1

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

    protected open fun initRecycler() {
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.isNestedScrollingEnabled = true
        //recyclerView.setItemViewCacheSize(10)
        recyclerView.setHasFixedSize(true)
    }

    protected fun initSwipeToRefresh() {
        viewModel.getInitialLoadState().observe(this, Observer {
            isLoading = it.status == LOADING
            swipeRefreshLayout.isRefreshing = isLoading

            isError = it.status == ERROR
            errorMessageResId = it.messageResId
            showError(true)
        })

        viewModel.getAfterLoadState().observe(this, Observer {
            isLoading = it.status == LOADING
            swipeRefreshLayout.isRefreshing = isLoading

            isError = it.status == ERROR
            errorMessageResId = it.messageResId
            showError(true)
        })

        swipeRefreshLayout.setOnRefreshListener {
            if (!isLoading) {
                viewModel.refresh()
                recyclerView.scrollToPosition(0)
            }
        }
    }

    protected fun showError(isVisibleToUser: Boolean, listener: View.OnClickListener = View.OnClickListener { retry() }) {
        if (isVisibleToUser && isError) {
            showErrorSnackbar(errorMessageResId, R.string.retry, listener)
        } else {
            dismissErrorSnackbar()
        }
    }

    protected fun retry() {
        viewModel.retry()
    }

}
