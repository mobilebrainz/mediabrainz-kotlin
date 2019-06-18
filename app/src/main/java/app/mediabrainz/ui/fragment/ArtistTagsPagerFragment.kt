package app.mediabrainz.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AutoCompleteTextView
import android.widget.ImageButton
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.viewpager.widget.ViewPager
import app.mediabrainz.api.xml.entity.TagVoteType
import app.mediabrainz.domain.model.Artist
import app.mediabrainz.domain.model.TagType
import app.mediabrainz.domain.repository.Resource
import app.mediabrainz.domain.repository.Resource.Status.*
import app.mediabrainz.ui.R
import app.mediabrainz.ui.adapter.pager.EditTagsPagerAdapter
import app.mediabrainz.ui.preference.GlobalPreferences
import app.mediabrainz.ui.preference.OAuthPreferences
import app.mediabrainz.ui.viewmodel.activity.TaggedVM
import app.mediabrainz.ui.viewmodel.event.ArtistEvent
import app.mediabrainz.ui.viewmodel.fragment.ArtistTagsPagerFragmentVM
import app.mediabrainz.ui.viewmodel.lookupRepository.ArtistLookupViewModel
import com.google.android.material.tabs.TabLayout


class ArtistTagsPagerFragment : BaseFragment() {

    private val TAGS_TAB = "ArtistTagsPagerFragment.TAGS_TAB"

    private val defaultTab = TagType.GENRE.ordinal
    private var tab = 0
    private lateinit var artist: Artist
    private lateinit var artistTagsPagerFragmentVM: ArtistTagsPagerFragmentVM
    private lateinit var taggedVM: TaggedVM
    private lateinit var artistLookupViewModel: ArtistLookupViewModel

    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var loginWarningView: TextView
    private lateinit var tagInputView: AutoCompleteTextView
    private lateinit var tagButton: ImageButton
    private lateinit var pagerView: ViewPager
    private lateinit var tabsView: TabLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflate(R.layout.edit_tags_pager_fragment, container)

        tab = if (savedInstanceState != null) savedInstanceState.getInt(TAGS_TAB, defaultTab)
        else defaultTab

        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout)
        loginWarningView = view.findViewById(R.id.loginWarningView)
        tagInputView = view.findViewById(R.id.tagInputView)
        tagButton = view.findViewById(R.id.tagButton)

        pagerView = view.findViewById(R.id.pagerView)
        tabsView = view.findViewById(R.id.tabsView)

        swipeRefreshLayout.isEnabled = false
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.let {

            artistTagsPagerFragmentVM = getViewModel(ArtistTagsPagerFragmentVM::class.java)
            taggedVM = getActivityViewModel(TaggedVM::class.java)

            artistLookupViewModel = getViewModel(ArtistLookupViewModel::class.java)

            observe()
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(TAGS_TAB, tab)
    }

    override fun onPause() {
        super.onPause()
        tab = pagerView.currentItem
    }

    override fun onStart() {
        super.onStart()
        loginWarningView.visibility = if (OAuthPreferences.isNotEmpty()) View.GONE else View.VISIBLE
    }

    private fun configTags() {
        val pagerAdapter = EditTagsPagerAdapter(childFragmentManager, resources)
        pagerView.adapter = pagerAdapter
        pagerView.offscreenPageLimit = pagerAdapter.count
        tabsView.setupWithViewPager(pagerView)
        tabsView.tabMode = TabLayout.MODE_FIXED
        pagerAdapter.setupTabViews(tabsView)
        pagerView.currentItem = tab
    }

    private fun postArtistTag(tag: String, voteType: TagVoteType) {
        artistTagsPagerFragmentVM.postArtistTag(tag, voteType)
    }

    fun observe() {
        getActivityViewModel(ArtistEvent::class.java).artist.observe(this,
            Observer { artistTagsPagerFragmentVM.artistld.value = it })

        artistTagsPagerFragmentVM.artistld.observe(this, Observer {
            artist = it
            setSubtitle(it.name)
            taggedVM.tagged = it
            configTags()
        })

        taggedVM.postTag.observe(this, Observer { it?.apply { postArtistTag(tag, voteType) } })

        artistTagsPagerFragmentVM.postTag.observe(this, Observer {
            it?.apply {
                when (status) {
                    LOADING -> swipeRefreshLayout.isRefreshing = true
                    SUCCESS -> {
                        if (data != null && data) {
                            artistLookupViewModel.lookupTags(artist.mbid)
                            //artistLookupViewModel.lookupTags(artist.mbid)
                            if (GlobalPreferences.isPropagateArtistTags()) {
                                //artistTagsPagerFragmentVM.propagateTagToAlbums(tag, voteType)
                            }
                        } else {
                            swipeRefreshLayout.isRefreshing = false
                            showInfoSnackbar(R.string.error_post_tag)
                        }
                        status = INVALID
                    }
                    ERROR -> {
                        swipeRefreshLayout.isRefreshing = false
                        showInfoSnackbar(R.string.connection_error)
                    }
                }
            }
        })

        artistLookupViewModel.artistTags.observe(this, Observer {
            it?.apply {
                when (status) {
                    SUCCESS -> {
                        data?.apply {
                            artist.tags = tags
                            artist.genres = genres
                            artist.userTags = userTags
                            artist.userGenres = userGenres

                            tab = pagerView.currentItem
                            configTags()
                        }
                        swipeRefreshLayout.isRefreshing = false
                        status = INVALID
                    }
                    ERROR -> {
                        swipeRefreshLayout.isRefreshing = false
                        showInfoSnackbar(R.string.connection_error)
                    }
                }
            }
        })

        artistTagsPagerFragmentVM.propagateTag.observe(this, Observer {
            it?.apply {

            }
        })
    }



}
