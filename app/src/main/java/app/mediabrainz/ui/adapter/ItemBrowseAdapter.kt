package app.mediabrainz.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import app.mediabrainz.domain.model.*
import app.mediabrainz.ui.R
import kotlinx.android.synthetic.main.test_adapter_row.view.*


class ItemBrowseAdapter :
    PagedListAdapter<Event, ItemBrowseAdapter.SearchViewHolder>(DiffUtilCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.test_adapter_row, parent, false)
        return SearchViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        getItem(position)?.let { holder.bindTo(it) }
    }

    class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val nameView = itemView.nameView

        fun bindTo(item: Event) {
            with(item) {
                nameView.text = name
            }
        }
    }

    class DiffUtilCallBack : DiffUtil.ItemCallback<Event>() {
        override fun areItemsTheSame(oldItem: Event, newItem: Event): Boolean {
            return oldItem.mbid == newItem.mbid
        }

        override fun areContentsTheSame(oldItem: Event, newItem: Event): Boolean {
            return oldItem.name == newItem.name
            //&& oldItem.score == newItem.score
            //&& oldItem.commentCount == newItem.commentCount
        }
    }
}

