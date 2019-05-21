package app.mediabrainz.ui.fragment

import android.os.Bundle
import androidx.lifecycle.Observer
import app.mediabrainz.api.browserequest.ReleaseBrowseEntityType
import app.mediabrainz.ui.adapter.ReleaseAdapter
import app.mediabrainz.ui.viewmodel.browseDataSource.PagedReleaseBrowseViewModel


class ReleasesFragment : BaseDataSourceFragment() {

    val TAG = "ReleasesFragment"

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        arguments?.let {
            val args = ReleasesFragmentArgs.fromBundle(it)
            // todo: set subtitle???
            //setSubtitle(args.subtitle)
            setSubtitle("")
            initDataSource(ReleaseBrowseEntityType.values()[args.type], args.mbid)
        }
    }

    private fun initDataSource(entityType: ReleaseBrowseEntityType, mbid: String) {
        val vm = getViewModel(PagedReleaseBrowseViewModel::class.java)
        vm.browse(entityType, mbid)
        viewModel = vm

        val adapter = ReleaseAdapter(this)
        adapter.holderClickListener = {
            if (!isLoading && !isError) {
                //navigate(ReleasesFragmentDirections.actionReleasesFragmentToReleaseGroupFragment(it.mbid))
            }
        }
        vm.pagedItems.observe(this, Observer { adapter.submitList(it) })
        recyclerView.adapter = adapter
        initSwipeToRefresh()
    }

}
