package app.mediabrainz.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import app.mediabrainz.domain.model.*
import app.mediabrainz.domain.model.Annotation
import app.mediabrainz.ui.R
import kotlinx.android.synthetic.main.artist_search_adapter_row.view.*


class ItemSearchAdapter :
    PagedListAdapter<Work, ItemSearchAdapter.SearchViewHolder>(DiffUtilCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.artist_search_adapter_row, parent, false)
        return SearchViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        getItem(position)?.let { holder.bindTo(it) }
    }

    class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val nameView = itemView.nameView

        fun bindTo(item: Work) {
            with(item) {
                nameView.text = name
            }
        }
    }

    class DiffUtilCallBack : DiffUtil.ItemCallback<Work>() {
        override fun areItemsTheSame(oldItem: Work, newItem: Work): Boolean {
            return oldItem.mbid == newItem.mbid
        }

        override fun areContentsTheSame(oldItem: Work, newItem: Work): Boolean {
            return oldItem.name == newItem.name
            //&& oldItem.score == newItem.score
            //&& oldItem.commentCount == newItem.commentCount
        }
    }
}

