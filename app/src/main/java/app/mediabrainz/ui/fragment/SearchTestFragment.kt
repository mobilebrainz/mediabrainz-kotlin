package app.mediabrainz.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import app.mediabrainz.domain.datasource.core.NetworkState.Status.ERROR
import app.mediabrainz.domain.datasource.core.NetworkState.Status.LOADING
import app.mediabrainz.ui.R
import app.mediabrainz.ui.adapter.ItemSearchAdapter
import app.mediabrainz.ui.viewmodel.searchDataSource.*


class SearchTestFragment : BaseFragment() {

    private var isLoading: Boolean = false
    private lateinit var adapter: ItemSearchAdapter
    private lateinit var viewModel: PagedArtistSearchViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_paged_recycler, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(PagedArtistSearchViewModel::class.java)
        viewModel.search("Black")

        adapter = ItemSearchAdapter()
        viewModel.pagedItems.observe(this, Observer { adapter.submitList(it) })

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.isNestedScrollingEnabled = true
        //recyclerView.setItemViewCacheSize(10)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter

        initSwipeToRefresh()
    }

    private fun initSwipeToRefresh() {
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

    private fun showError(show: Boolean, @StringRes messageResId: Int) {
        if (show) {
            showErrorSnackbar(messageResId, R.string.retry, View.OnClickListener { retry() })
        } else {
            dismissErrorSnackbar()
        }
    }

    private fun retry() {
        viewModel.retry()
    }

}
