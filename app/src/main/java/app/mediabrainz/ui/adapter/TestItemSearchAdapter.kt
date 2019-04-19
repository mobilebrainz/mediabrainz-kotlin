package app.mediabrainz.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import app.mediabrainz.domain.model.Artist
import app.mediabrainz.ui.R
import app.mediabrainz.ui.extension.findViewById


class TestItemSearchAdapter :
    PagedListAdapter<Artist, TestItemSearchAdapter.SearchViewHolder>(DiffUtilCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.test_adapter_row, parent, false)
        return SearchViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        getItem(position)?.let { holder.bindTo(it) }
    }

    class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val nameView: TextView = findViewById(R.id.nameView)

        fun bindTo(item: Artist) {
            with(item) {
                nameView.text = name
            }
        }
    }

    class DiffUtilCallBack : DiffUtil.ItemCallback<Artist>() {
        override fun areItemsTheSame(oldItem: Artist, newItem: Artist): Boolean {
            return oldItem.mbid == newItem.mbid
        }

        override fun areContentsTheSame(oldItem: Artist, newItem: Artist): Boolean {
            return oldItem.name == newItem.name
            //&& oldItem.score == newItem.score
            //&& oldItem.commentCount == newItem.commentCount
        }
    }
}

