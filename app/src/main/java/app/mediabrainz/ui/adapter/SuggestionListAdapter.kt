package app.mediabrainz.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.TextView
import app.mediabrainz.db.entity.Suggestion
import app.mediabrainz.db.entity.SuggestionField
import app.mediabrainz.domain.repository.dbRepository.SuggestionRepository
import app.mediabrainz.ui.App
import app.mediabrainz.ui.R


class SuggestionListAdapter(
    context: Context,
    val suggestionField: SuggestionField,
    val dataList: ArrayList<Suggestion> = ArrayList()
) : ArrayAdapter<Suggestion>(context, LAYOUT_ID, dataList) {

    companion object {
        const val LAYOUT_ID = R.layout.layout_dropdown_item
    }

    private val listFilter = ListFilter()

    override fun getFilter() = listFilter

    override fun getCount() = dataList.size

    override fun getItem(position: Int) = if (position < dataList.size) dataList[position] else null

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val v = view ?: LayoutInflater.from(parent.context).inflate(LAYOUT_ID, parent, false)
        getItem(position)?.apply {
            v.findViewById<TextView>(R.id.dropdownItemView).text = word
        }
        return v
    }

    inner class ListFilter : Filter() {

        private val lock = Any()
        private val suggestionRepository = SuggestionRepository(App.instance)

        override fun performFiltering(prefix: CharSequence?): FilterResults {
            val results = FilterResults()
            if (prefix == null || prefix.isEmpty()) {
                synchronized(lock) {
                    results.values = ArrayList<Suggestion>()
                }
            } else {
                results.values = suggestionRepository
                    .findByWordAndField(prefix.toString().toLowerCase(), suggestionField)
            }
            return results
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults) {
            dataList.clear()
            results.values?.let {
                val suggestions = it as ArrayList<Suggestion>
                if (suggestions.isNotEmpty()) {
                    dataList.addAll(suggestions)
                    notifyDataSetChanged()
                } else {
                    notifyDataSetInvalidated()
                }
            }
        }
    }
}