package app.mediabrainz.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import app.mediabrainz.domain.model.Artist
import app.mediabrainz.domain.repository.Resource.Status.*
import app.mediabrainz.ui.R
import app.mediabrainz.ui.viewmodel.lookupRepository.ArtistLookupViewModel


class ArtistFragment : BaseFragment(), View.OnClickListener {

    private val TAG = "ArtistFragment"

    private lateinit var artist: Artist
    private var isError: Boolean = false
    private lateinit var artistLookupViewModel: ArtistLookupViewModel
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflate(R.layout.artist_fragment, container)
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setSubtitle("")
        arguments?.let {
            artistLookupViewModel = getViewModel(ArtistLookupViewModel::class.java)
            observeArtistLookupViewModel(artistLookupViewModel)
            artistLookupViewModel.lookup(ArtistFragmentArgs.fromBundle(it).mbid)

            swipeRefreshLayout.setOnRefreshListener { artistLookupViewModel.lookup() }
            initMenu()
        }
    }

    private fun observeArtistLookupViewModel(vm: ArtistLookupViewModel) {
        artistLookupViewModel.result.observe(this, Observer {
            it?.apply {
                isError = false
                dismissErrorSnackbar()
                swipeRefreshLayout.isRefreshing = status == LOADING
                when (status) {
                    SUCCESS -> {
                        data?.apply {
                            artist = this
                            setSubtitle(artist.name)
                            show()
                            insertNestedFragments()
                        }
                    }
                    ERROR -> {
                        isError = true
                        showErrorSnackbar(
                            R.string.connection_error,
                            R.string.connection_error_retry,
                            View.OnClickListener { artistLookupViewModel.lookup() })
                    }
                }
            }
        })
    }

    private fun show() {
        view?.let {
            val artistNameView: TextView = it.findViewById(R.id.artistNameView)
            artistNameView.text = artist.name
        }
    }

    private fun insertNestedFragments() {
        /*
        getActivityViewModel(ArtistRatingsVM::class.java).artist.setValue(artist)
        val transaction = childFragmentManager.beginTransaction()
        transaction.replace(R.id.artistRatingsFragment, ArtistRatingsFragment.newInstance())
            .commit()
        */
    }

    private fun initMenu() {
        view?.let {
            it.findViewById<LinearLayout>(R.id.releasesItem).setOnClickListener(this)
            it.findViewById<LinearLayout>(R.id.relationsItem).setOnClickListener(this)
            it.findViewById<LinearLayout>(R.id.tagsItem).setOnClickListener(this)
            it.findViewById<LinearLayout>(R.id.linksItem).setOnClickListener(this)
            it.findViewById<LinearLayout>(R.id.wikiItem).setOnClickListener(this)
            it.findViewById<LinearLayout>(R.id.addToCollectionItem).setOnClickListener(this)
            it.findViewById<LinearLayout>(R.id.shareItem).setOnClickListener(this)
        }
    }

    override fun onClick(v: View?) {
        if (v != null && !swipeRefreshLayout.isRefreshing && !isError) {
            when (v.id) {
                R.id.releasesItem -> {
                    /*
                    if (artist.getReleaseGroups() != null && !artist.getReleaseGroups().isEmpty()) {
                        val releasesAction = ArtistFragmentDirections.actionArtistFragmentToArtistReleasesFragment(
                            artist.getId(),
                            artist.getName()
                        )
                        navigate(releasesAction)
                    } else {
                        showInfoSnackbar(R.string.no_results)
                    }
                    */
                }
                R.id.relationsItem -> {
                    /*
                    val relations = RelationExtractor(artist).getArtistRelations()
                    if (!relations.isEmpty()) {
                        getActivityViewModel(ArtistRelationsEvent::class.java).relations.setValue(relations)
                        val relActin =
                            ArtistFragmentDirections.actionArtistFragmentToArtistRelationsFragment(artist.getName())
                        navigate(relActin)
                    } else {
                        showInfoSnackbar(R.string.no_results)
                    }
                    */
                }
                R.id.tagsItem -> {
                    /*
                    getActivityViewModel(ArtistEvent::class.java).artist.setValue(artist)
                    navigate(R.id.action_artistFragment_to_artistTagsPagerFragment)
                    */
                }
                R.id.linksItem -> {
                    /*
                    val urls = RelationExtractor(artist).getUrls()
                    if (!urls.isEmpty()) {
                        getActivityViewModel(LinksEvent::class.java).urls.setValue(urls)
                        val linksAction = NavGraphDirections.actionGlobalLinksFragment(artist.getName())
                        navigate(linksAction)
                    } else {
                        showInfoSnackbar(R.string.no_results)
                    }
                    */
                }
                R.id.wikiItem -> {
                    /*
                    getActivityViewModel(LinksEvent::class.java).urls.setValue(RelationExtractor(artist).getUrls())
                    val wikiAction = NavGraphDirections.actionGlobalWikiFragment(artist.getName())
                    navigate(wikiAction)
                    */
                }
                R.id.addToCollectionItem -> {

                }
                R.id.shareItem -> {

                }
            }
        }

    }


}
