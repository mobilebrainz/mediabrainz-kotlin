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
import app.mediabrainz.ui.R
import app.mediabrainz.ui.preference.OAuthPreferences
import app.mediabrainz.ui.viewmodel.activity.TaggedVM
import app.mediabrainz.ui.viewmodel.event.ArtistEvent
import com.google.android.material.tabs.TabLayout


class ArtistTagsPagerFragment : BaseFragment() {

    private val TAGS_TAB = "ArtistTagsPagerFragment.TAGS_TAB"

    private var tab = 0

    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var loginWarningView: TextView
    private lateinit var tagInputView: AutoCompleteTextView
    private lateinit var tagButton: ImageButton
    private lateinit var pagerView: ViewPager
    private lateinit var tabsView: TabLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflate(R.layout.edit_tags_pager_fragment, container)

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
                getActivityViewModel(ArtistEvent::class.java).artist
                    .observe(this, Observer {
                        setSubtitle(it.name)
                        getActivityViewModel(TaggedVM::class.java).tagged = it
                        //todo

                    })
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

}
