package app.mediabrainz.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Spinner
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import app.mediabrainz.ui.R
import app.mediabrainz.ui.extension.hideKeyboard
import app.mediabrainz.ui.extension.trim


class SearchFragment : BaseFragment() {

    private val TAG = "SearchFragment"

    private var isLoading: Boolean = false
    private var isError: Boolean = false

    private lateinit var artistFieldView: AutoCompleteTextView
    private lateinit var albumFieldView: AutoCompleteTextView
    private lateinit var trackFieldView: AutoCompleteTextView
    private lateinit var queryInputView: AutoCompleteTextView
    private lateinit var searchSpinner: Spinner
    private lateinit var adapter: ArrayAdapter<String>
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflate(R.layout.search_fragment, container)

        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout)
        artistFieldView = view.findViewById(R.id.artistFieldView)
        albumFieldView = view.findViewById(R.id.albumFieldView)
        trackFieldView = view.findViewById(R.id.trackFieldView)
        searchSpinner = view.findViewById(R.id.searchSpinner)
        queryInputView = view.findViewById(R.id.queryInputView)

        queryInputView.setOnEditorActionListener { _, _, _ -> selectedSearch() }
        view.findViewById<View>(R.id.selectedSearchButton).setOnClickListener { selectedSearch() }
        view.findViewById<View>(R.id.inputSearchButton).setOnClickListener { inputSearch() }

        swipeRefreshLayout.isEnabled = false

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    private fun selectedSearch(): Boolean {
        if (isLoading || isError) return false
        /*
        val query = queryInputView.text.toString().trim { it <= ' ' }.toLowerCase()
        if (query.isNotBlank()) {
            if (activity != null) {
                UiUtils.hideKeyboard(activity)
            }
            if (genres.contains(query)) {
                //ActivityFactory.startTagActivity(getContext(), query, true);
            } else {
                val action =
                    SearchFragmentDirections.actionSearchFragmentToResultSearchFragment(null, null, null, query)
                action.setSearchType(SearchType.values()[searchSpinner.selectedItemPosition].ordinal())
                navigate(action)
            }
        }
        */
        return false
    }

    private fun inputSearch() {
        if (isLoading || isError) return

        val artist = artistFieldView.trim()
        val album = albumFieldView.trim()
        val track = trackFieldView.trim()

        if (track.isNotBlank() || album.isNotBlank() || artist.isNotBlank()) {
            activity?.apply { hideKeyboard() }
            navigate(artist, album, track)
        }
    }

    private fun navigate(artist: String, album: String, track: String, query: String = "", searchType: Int = -1) {
        navigate(
            SearchFragmentDirections
                .actionSearchFragmentToResultSearchFragment(artist, album, track, "", searchType)
        )
    }

}