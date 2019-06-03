package app.mediabrainz.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.viewpager.widget.ViewPager
import app.mediabrainz.domain.model.Artist
import app.mediabrainz.domain.model.ArtistArtistRelationshipType
import app.mediabrainz.domain.model.ArtistArtistRelationshipType.*
import app.mediabrainz.domain.model.Relation
import app.mediabrainz.ui.R
import app.mediabrainz.ui.adapter.pager.RelationPagerAdapter
import app.mediabrainz.ui.viewmodel.RelationPagerFragmentVM
import app.mediabrainz.ui.viewmodel.event.ArtistRelationsEvent
import com.google.android.material.tabs.TabLayout


class RelationPagerFragment : BaseFragment() {

    private val TAG = "RelationPagerF"
    private val TAB = "RelationPagerFragment.TAB"

    val items = hashMapOf<ArtistArtistRelationshipType, ArrayList<Relation<Artist>>>(
        Pair(CURRENT_MEMBER_OF_BANDS, ArrayList()),
        Pair(PAST_MEMBER_OF_BANDS, ArrayList()),
        Pair(SUBGROUPS, ArrayList()),
        Pair(CONDUCTOR_POSITIONS, ArrayList()),
        Pair(FOUNDERS, ArrayList()),
        Pair(SUPPORTING_MUSICIANS, ArrayList()),
        Pair(VOCAL_SUPPORTING_MUSICIANS, ArrayList()),
        Pair(INSTRUMENTAL_SUPPORTING_MUSICIANS, ArrayList()),
        Pair(TRIBUTES, ArrayList()),
        Pair(VOICE_ACTORS, ArrayList()),
        Pair(COLLABORATIONS, ArrayList()),
        Pair(IS_PERSONS, ArrayList()),
        Pair(TEACHERS, ArrayList()),
        Pair(COMPOSER_IN_RESIDENCES, ArrayList())
    )

    private var tab = 0
    private lateinit var pagerView: ViewPager
    private lateinit var tabsView: TabLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflate(R.layout.fragment_pager_without_icons, container)
        savedInstanceState?.let {
            tab = it.getInt(TAB, 0)
        }
        pagerView = view.findViewById(R.id.pagerView)
        tabsView = view.findViewById(R.id.tabsView)
        return view
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(TAB, tab)
    }

    override fun onPause() {
        super.onPause()
        tab = pagerView.currentItem
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        arguments?.let {
            val args = RelationPagerFragmentArgs.fromBundle(it)
            setSubtitle(args.subTitle ?: "")

            val relationPagerFragmentVM = getViewModel(RelationPagerFragmentVM::class.java)
            getActivityViewModel(ArtistRelationsEvent::class.java).artistRelations
                .observe(this, Observer { relationPagerFragmentVM.artistRelations.value = it })

            relationPagerFragmentVM.artistRelations.observe(this, Observer { show(it) })
        }
    }

    private fun show(rels: List<Relation<Artist>>) {
        for (rel in rels) {
            when (rel.type ?: "".toLowerCase()) {
                CURRENT_MEMBER_OF_BANDS.type.type -> {
                    if (rel.ended || rel.end.isNotEmpty()) {
                        items[PAST_MEMBER_OF_BANDS]!!.add(rel)
                    } else {
                        items[CURRENT_MEMBER_OF_BANDS]!!.add(rel)
                    }
                }
                SUBGROUPS.type.type -> items[SUBGROUPS]!!.add(rel)
                CONDUCTOR_POSITIONS.type.type -> items[CONDUCTOR_POSITIONS]!!.add(rel)
                FOUNDERS.type.type -> items[FOUNDERS]!!.add(rel)
                SUPPORTING_MUSICIANS.type.type -> items[SUPPORTING_MUSICIANS]!!.add(rel)
                VOCAL_SUPPORTING_MUSICIANS.type.type -> items[VOCAL_SUPPORTING_MUSICIANS]!!.add(rel)
                INSTRUMENTAL_SUPPORTING_MUSICIANS.type.type -> items[INSTRUMENTAL_SUPPORTING_MUSICIANS]!!.add(rel)
                TRIBUTES.type.type -> items[TRIBUTES]!!.add(rel)
                VOICE_ACTORS.type.type -> items[VOICE_ACTORS]!!.add(rel)
                COLLABORATIONS.type.type -> items[COLLABORATIONS]!!.add(rel)
                IS_PERSONS.type.type -> items[IS_PERSONS]!!.add(rel)
                TEACHERS.type.type -> items[TEACHERS]!!.add(rel)
                COMPOSER_IN_RESIDENCES.type.type -> items[COMPOSER_IN_RESIDENCES]!!.add(rel)
            }
        }

        val keys = ArrayList<ArtistArtistRelationshipType>()
        for (item in items) {
            if (item.value.isNotEmpty()) keys.add(item.key)
        }
        val pagerAdapter = RelationPagerAdapter(childFragmentManager, resources, keys)
        pagerView.adapter = pagerAdapter
        pagerView.offscreenPageLimit = pagerAdapter.count
        pagerView.currentItem = tab
        tabsView.setupWithViewPager(pagerView)
        pagerAdapter.setupTabViews(tabsView)
    }

}
