package app.mediabrainz.ui.fragment

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import app.mediabrainz.domain.model.ReleaseGroup
import app.mediabrainz.domain.repository.Resource
import app.mediabrainz.ui.adapter.pager.ReleaseGroupAdapter
import app.mediabrainz.ui.adapter.pager.ReleaseGroupsPagerAdapter
import app.mediabrainz.ui.preference.GlobalPreferences
import app.mediabrainz.ui.preference.GlobalPreferences.RELEASE_GROUP_OFFICIAL
import app.mediabrainz.ui.preference.OAuthPreferences
import app.mediabrainz.ui.viewmodel.browseDataSource.PagedReleaseGroupsByArtistAndTypeViewModel
import app.mediabrainz.ui.viewmodel.searchRepository.ReleaseGroupSearchViewModel


class ReleaseGroupsTabFragment :
    BaseLazyDataSourceFragment(),
    SharedPreferences.OnSharedPreferenceChangeListener {

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
        if (GlobalPreferences.isReleaseGroupOfficial()) {
            val rgSearchViewModel: ReleaseGroupSearchViewModel = getViewModel(ReleaseGroupSearchViewModel::class.java)
            rgSearchViewModel.result.observe(this, Observer {
                it?.apply {
                    isLoading = status == Resource.Status.LOADING
                    swipeRefreshLayout.isRefreshing = isLoading

                    isError = status == Resource.Status.ERROR
                    showError(true, messageResId,
                        View.OnClickListener { rgSearchViewModel.searchOfficialReleaseGroups() })

                    if (status == Resource.Status.SUCCESS) {
                        data?.apply {
                            initDataSource(this)
                        }
                    }
                }
            })
            if (artistMbid != null && releaseGroupType != null) {
                rgSearchViewModel.searchOfficialReleaseGroups(artistMbid!!, releaseGroupType!!.type, 0, 100)
            }
        } else initDataSource(null)

    }

    private fun initDataSource(releaseGroups: List<ReleaseGroup>?) {
        if (artistMbid != null && releaseGroupType != null) {
            val vm = getViewModel(PagedReleaseGroupsByArtistAndTypeViewModel::class.java)
            vm.browse(artistMbid!!, releaseGroupType!!.type, releaseGroups, OAuthPreferences.isNotEmpty())
            viewModel = vm

            val adapter = ReleaseGroupAdapter(this)
            adapter.holderClickListener = {
                if (!isLoading && !isError) {
                    //navigate(NavGraphDirections.actionGlobalArtistFragment(it.mbid))
                }
            }
            vm.pagedItems.observe(this, Observer { adapter.submitList(it) })
            recyclerView.adapter = adapter
            initSwipeToRefresh()
        }
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences, key: String) {
        if (key == RELEASE_GROUP_OFFICIAL) {
            if (!loadView()) {
                recyclerView.adapter = null
                isLoaded = false
            }
            /*
            if (getUserVisibleHint()) {
                mutableIsOfficial.setValue(MediaBrainzApp.getPreferences().isReleaseGroupOfficial());
                releaseGroupsVM.refresh();
                swipeRefreshLayout.setRefreshing(false);
                pagedRecycler.scrollToPosition(0);
            } else {
                pagedRecycler.setAdapter(null);
                setLoaded(false);
            }
            */
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        GlobalPreferences.unregisterOnSharedPreferenceChangeListener(this)
    }

}
