package app.mediabrainz.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Spinner
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import app.mediabrainz.db.entity.SuggestionField
import app.mediabrainz.db.entity.SuggestionField.*
import app.mediabrainz.ui.R
import app.mediabrainz.ui.adapter.SuggestionListAdapter
import app.mediabrainz.ui.extension.hideKeyboard
import app.mediabrainz.ui.extension.trim
import app.mediabrainz.ui.preference.GlobalPreferences
import java.util.*


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
        setSubtitle("")
        setupSearchTypeSpinner()
    }

    private fun setupSearchTypeSpinner() {
        val types = ArrayList<CharSequence>()
        for (searchType in SearchType.values()) {
            types.add(resources.getText(searchType.res))
        }
        val typeAdapter = ArrayAdapter(activity!!, android.R.layout.simple_spinner_item, types)
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        searchSpinner.adapter = typeAdapter

        searchSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>, view: View?, pos: Int, id: Long) {
                context?.let {
                    queryInputView.threshold = 2
                    when (pos) {
                        SearchType.ANNOTATION.ordinal ->
                            queryInputView.setAdapter(SuggestionListAdapter(it, ANNOTATION))

                        SearchType.AREA.ordinal ->
                            queryInputView.setAdapter(SuggestionListAdapter(it, AREA))

                        SearchType.BARCODE.ordinal ->
                            queryInputView.setAdapter(SuggestionListAdapter(it, BARCODE))

                        SearchType.CDSTUB.ordinal ->
                            queryInputView.setAdapter(SuggestionListAdapter(it, CDSTUB))

                        SearchType.EVENT.ordinal ->
                            queryInputView.setAdapter(SuggestionListAdapter(it, EVENT))

                        SearchType.INSTRUMENT.ordinal ->
                            queryInputView.setAdapter(SuggestionListAdapter(it, INSTRUMENT))

                        SearchType.LABEL.ordinal ->
                            queryInputView.setAdapter(SuggestionListAdapter(it, LABEL))

                        SearchType.PLACE.ordinal ->
                            queryInputView.setAdapter(SuggestionListAdapter(it, PLACE))

                        SearchType.SERIES.ordinal ->
                            queryInputView.setAdapter(SuggestionListAdapter(it, SERIES))

                        SearchType.TAG.ordinal ->
                            queryInputView.setAdapter(SuggestionListAdapter(it, SuggestionField.TAG))

                        SearchType.URL.ordinal ->
                            queryInputView.setAdapter(SuggestionListAdapter(it, URL))

                        SearchType.WORK.ordinal ->
                            queryInputView.setAdapter(SuggestionListAdapter(it, WORK))

                        //SearchType.USER.ordinal ->
                        //    queryInputView.setAdapter(SuggestionListAdapter(it, USER))

                        else ->
                            queryInputView.setAdapter(
                                ArrayAdapter(it, R.layout.layout_dropdown_item, arrayOf<String>())
                            )
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }

    private fun selectedSearch(): Boolean {
        if (isLoading || isError) return false
        val query = queryInputView.trim().toLowerCase()
        if (query.isNotBlank()) {
            activity?.hideKeyboard()
            val searchType = SearchType.values()[searchSpinner.selectedItemPosition].ordinal
            navigate("", "", "", query, searchType)
        }
        return false
    }

    private fun inputSearch() {
        if (isLoading || isError) return

        val artist = artistFieldView.trim()
        val album = albumFieldView.trim()
        val track = trackFieldView.trim()

        if (track.isNotBlank() || album.isNotBlank() || artist.isNotBlank()) {
            activity?.hideKeyboard()
            navigate(artist, album, track)
        }
    }

    private fun navigate(artist: String, album: String, track: String, query: String = "", searchType: Int = -1) {
        navigate(
            SearchFragmentDirections
                .actionSearchFragmentToResultSearchFragment(artist, album, track, query, searchType)
        )
    }

    override fun onResume() {
        super.onResume()
        context?.let {
            if (GlobalPreferences.isSearchSuggestionsEnabled()) {
                artistFieldView.setAdapter(SuggestionListAdapter(it, ARTIST))
                albumFieldView.setAdapter(SuggestionListAdapter(it, RELEASE_GROUP))
                trackFieldView.setAdapter(SuggestionListAdapter(it, RECORDING))
            } else {
                artistFieldView.setAdapter(ArrayAdapter(it, R.layout.layout_dropdown_item, arrayOf<String>()))
                albumFieldView.setAdapter(ArrayAdapter(it, R.layout.layout_dropdown_item, arrayOf<String>()))
                trackFieldView.setAdapter(ArrayAdapter(it, R.layout.layout_dropdown_item, arrayOf<String>()))
            }
        }
    }

}