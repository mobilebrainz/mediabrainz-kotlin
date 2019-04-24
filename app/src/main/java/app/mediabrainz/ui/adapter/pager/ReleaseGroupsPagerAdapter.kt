package app.mediabrainz.ui.adapter.pager

import android.content.res.Resources
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import app.mediabrainz.domain.model.RGPrimaryType
import app.mediabrainz.domain.model.RGSecondaryType
import app.mediabrainz.domain.model.RGType
import app.mediabrainz.ui.core.adapter.UpdatableFragmentPagerAdapter
import app.mediabrainz.ui.fragment.ReleaseGroupsTabFragment


class ReleaseGroupsPagerAdapter(
    fm: FragmentManager,
    resources: Resources,
    private val artistMbid: String
) : UpdatableFragmentPagerAdapter(ReleaseTab.values().size, fm, resources) {

    private val releaseTabs = ReleaseTab.values()

    init {
        for (i in releaseTabs.indices) {
            tabTitles[i] = releaseTabs[i].type.id
        }
    }

    override fun getItem(position: Int): Fragment {
        return releaseTabs[position].createFragment(artistMbid)
    }

    enum class ReleaseTab(val type: RGType) {
        ALBUMS(RGPrimaryType.ALBUM),
        EPS(RGPrimaryType.EP),
        SINGLES(RGPrimaryType.SINGLE),
        LIVES(RGSecondaryType.LIVE),
        COMPILATIONS(RGSecondaryType.COMPILATION);

        fun createFragment(artistMbid: String): Fragment {
            return ReleaseGroupsTabFragment.newInstance(ordinal, artistMbid)
        }
    }

}