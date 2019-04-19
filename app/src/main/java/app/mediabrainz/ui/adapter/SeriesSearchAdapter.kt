package app.mediabrainz.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import app.mediabrainz.domain.model.Series
import app.mediabrainz.ui.R
import app.mediabrainz.ui.extension.findViewById


class SeriesSearchAdapter : BasePagedListAdapter<Series>(DiffUtilCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.test_adapter_row, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(itemView: View) : BaseViewHolder<Series>(itemView) {

        val nameView: TextView = findViewById(R.id.nameView)

        override fun bindTo(item: Series) {
            with(item) {
                nameView.text = name
            }
        }
    }

    class DiffUtilCallBack : DiffUtil.ItemCallback<Series>() {
        override fun areItemsTheSame(oldItem: Series, newItem: Series): Boolean {
            return oldItem.mbid == newItem.mbid
        }

        override fun areContentsTheSame(oldItem: Series, newItem: Series): Boolean {
            return oldItem.name == newItem.name
            //&& oldItem.score == newItem.score
            //&& oldItem.commentCount == newItem.commentCount
        }
    }
}

