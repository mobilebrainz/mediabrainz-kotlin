package app.mediabrainz.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import app.mediabrainz.domain.repository.Resource
import app.mediabrainz.ui.adapter.TestPagedAdapter
import app.mediabrainz.ui.adapter.pager.ReleaseGroupsPagerAdapter
import app.mediabrainz.ui.viewmodel.browseDataSource.PagedReleaseGroupsByArtistAndTypeViewModel
import app.mediabrainz.ui.viewmodel.searchRepository.ReleaseGroupSearchViewModel


class ReleaseGroupsTabFragment : BaseLazyDataSourceFragment() {

    companion object {
        private const val RELEASES_TAB = "ReleaseGroupsTabFragment.RELEASES_TAB"
        private const val ARTIST_MBID = "ReleaseGroupsTabFragment.ARTIST_MBID"

        fun newInstance(releasesTab: Int, artistMbid: String): ReleaseGroupsTabFragment {
            val args = Bundle()
            args.putInt(RELEASES_TAB, releasesTab)
            args.putString(ARTIST_MBID, artistMbid)
            val fragment = ReleaseGroupsTabFragment()
            fragment.arguments = args
            return fragment
        }
    }

    val TAG = "ReleaseGroupsTabF"

    private var artistMbid: String? = null
    private var releaseGroupType: ReleaseGroupsPagerAdapter.ReleaseTab? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        arguments?.let {
            artistMbid = it.getString(ARTIST_MBID)
            releaseGroupType = ReleaseGroupsPagerAdapter.ReleaseTab.values()[it.getInt(RELEASES_TAB)]
            loadView()
        }
    }

    override fun initRecycler() {
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        recyclerView.isNestedScrollingEnabled = true
        //recyclerView.setItemViewCacheSize(10)
        recyclerView.setHasFixedSize(true)
    }

    override fun lazyLoad() {
        val rgSearchViewModel: ReleaseGroupSearchViewModel = getViewModel(ReleaseGroupSearchViewModel::class.java)
        rgSearchViewModel.result.observe(this, Observer {
            it?.apply {
                isLoading = status == Resource.Status.LOADING
                swipeRefreshLayout.isRefreshing = isLoading

                isError = status == Resource.Status.ERROR
                errorMessageResId = messageResId
                showError(true,
                    View.OnClickListener { rgSearchViewModel.searchOfficialReleaseGroups() })

                if (status == Resource.Status.SUCCESS) {
                    data?.apply {
                        //todo: filter(it)
                        initDataSource()
                    }
                }
            }
        })
        if (artistMbid != null && releaseGroupType != null) {
            rgSearchViewModel.searchOfficialReleaseGroups(artistMbid!!, releaseGroupType!!.type, 0, 100)
        }

    }

    private fun initDataSource() {
        val vm = getViewModel(PagedReleaseGroupsByArtistAndTypeViewModel::class.java)
        vm.browse(artistMbid!!, releaseGroupType!!.type, false)
        viewModel = vm

        val adapter = TestPagedAdapter()
        /*
        adapter.holderClickListener = {
            if (!isLoading && !isError) {

            }
        }
        */
        vm.pagedItems.observe(this, Observer { adapter.submitList(it) })
        recyclerView.adapter = adapter
        initSwipeToRefresh()
    }

}
