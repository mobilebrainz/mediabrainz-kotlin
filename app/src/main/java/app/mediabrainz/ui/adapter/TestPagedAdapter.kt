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


class TestPagedAdapter :
    PagedListAdapter<ReleaseGroup, TestPagedAdapter.BrowseViewHolder>(DiffUtilCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrowseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.test_adapter_row, parent, false)
        return BrowseViewHolder(view)
    }

    override fun onBindViewHolder(holder: BrowseViewHolder, position: Int) {
        getItem(position)?.let { holder.bindTo(it) }
    }

    class BrowseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val nameView = itemView.nameView

        fun bindTo(item: ReleaseGroup) {
            with(item) {
                nameView.text = name
            }
        }
    }

    class DiffUtilCallBack : DiffUtil.ItemCallback<ReleaseGroup>() {
        override fun areItemsTheSame(oldItem: ReleaseGroup, newItem: ReleaseGroup): Boolean {
            return oldItem.mbid == newItem.mbid
        }

        override fun areContentsTheSame(oldItem: ReleaseGroup, newItem: ReleaseGroup): Boolean {
            return oldItem.name == newItem.name
            //&& oldItem.score == newItem.score
            //&& oldItem.commentCount == newItem.commentCount
        }
    }
}

