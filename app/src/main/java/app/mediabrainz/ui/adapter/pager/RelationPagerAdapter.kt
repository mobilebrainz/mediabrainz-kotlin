package app.mediabrainz.ui.adapter.pager

import android.content.res.Resources
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import app.mediabrainz.domain.model.ArtistArtistRelationshipType
import app.mediabrainz.ui.core.adapter.UpdatableFragmentPagerAdapter
import app.mediabrainz.ui.fragment.RelationTabFragment


class RelationPagerAdapter(
    fm: FragmentManager,
    resources: Resources,
    private val relationTabs: List<ArtistArtistRelationshipType>
) : UpdatableFragmentPagerAdapter(relationTabs.size, fm, resources) {

    init {
        for (i in relationTabs.indices) {
            tabTitles[i] = relationTabs[i].tabRes
        }
    }

    override fun getItem(position: Int): Fragment {
        return RelationTabFragment.newInstance(relationTabs[position].ordinal)
    }

}