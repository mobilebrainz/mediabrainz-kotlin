package app.mediabrainz.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.mediabrainz.domain.model.ArtistArtistRelationshipType
import app.mediabrainz.ui.R
import app.mediabrainz.ui.adapter.ArtistRelationsAdapter


class RelationTabFragment : BaseFragment() {

    companion object {
        private const val REL_TAB = "RelationTabFragment.REL_TAB"

        fun newInstance(relTab: Int): RelationTabFragment {
            val args = Bundle()
            args.putInt(REL_TAB, relTab)
            val fragment = RelationTabFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var recyclerView: RecyclerView
    private lateinit var headerTitleView: TextView
    private lateinit var infoView: ImageView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflate(R.layout.relation_tab_fragment, container)
        recyclerView = view.findViewById(R.id.recyclerView)
        headerTitleView = view.findViewById(R.id.headerTitleView)
        infoView = view.findViewById(R.id.infoView)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        arguments?.let {
            val type = ArtistArtistRelationshipType.values()[it.getInt(REL_TAB)]

            val items = (parentFragment as RelationPagerFragment).items[type]
            val adapter = ArtistRelationsAdapter(items!!)
            adapter.holderClickListener = {
                navigate(
                    RelationPagerFragmentDirections
                        .actionArtistRelationsFragmentToArtistFragment(it.relation.mbid)
                )
            }
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.setHasFixedSize(true)
            recyclerView.adapter = adapter


            headerTitleView.text = getString(type.relationRes)
            infoView.setOnClickListener { showInfoSnackbar(type.descriptionRes, 15000, 6) }
        }
    }

}
