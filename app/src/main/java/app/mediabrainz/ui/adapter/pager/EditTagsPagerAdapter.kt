package app.mediabrainz.ui.adapter.pager

import android.content.res.Resources
import androidx.fragment.app.FragmentManager
import app.mediabrainz.domain.model.TagType
import app.mediabrainz.ui.core.adapter.UpdatableFragmentPagerAdapter
import app.mediabrainz.ui.fragment.EditTagsTabFragment


class EditTagsPagerAdapter(
    fm: FragmentManager,
    resources: Resources,
    private val tabs: Array<TagType> = TagType.values()
) : UpdatableFragmentPagerAdapter(tabs.size, fm, resources) {

    init {
        for (i in tabs.indices) {
            tabTitles[i] = tabs[i].titleRes
        }
    }

    override fun getItem(position: Int) =
        EditTagsTabFragment.newInstance(tabs[position].ordinal)

}

