package app.mediabrainz.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import app.mediabrainz.domain.model.Url
import app.mediabrainz.ui.R


class UrlSearchAdapter : BasePagedListAdapter<Url>(DiffUtilCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.test_adapter_row, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(itemView: View) : BaseViewHolder<Url>(itemView) {

        val nameView: TextView = itemView.findViewById(R.id.nameView)

        override fun bindTo(item: Url) {
            with(item) {
                nameView.text = resource
            }
        }
    }

    class DiffUtilCallBack : DiffUtil.ItemCallback<Url>() {
        override fun areItemsTheSame(oldItem: Url, newItem: Url): Boolean {
            return oldItem.mbid == newItem.mbid
        }

        override fun areContentsTheSame(oldItem: Url, newItem: Url): Boolean {
            return oldItem.resource == newItem.resource
            //&& oldItem.score == newItem.score
            //&& oldItem.commentCount == newItem.commentCount
        }
    }
}

