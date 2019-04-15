package app.mediabrainz.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import app.mediabrainz.domain.model.Area
import app.mediabrainz.ui.R
import kotlinx.android.synthetic.main.test_adapter_row.view.*


class AreaSearchAdapter : BasePagedListAdapter<Area>(DiffUtilCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.test_adapter_row, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(itemView: View) : BaseViewHolder<Area>(itemView) {

        val nameView = itemView.nameView

        override fun bindTo(item: Area) {
            with(item) {
                nameView.text = name
            }
        }
    }

    class DiffUtilCallBack : DiffUtil.ItemCallback<Area>() {
        override fun areItemsTheSame(oldItem: Area, newItem: Area): Boolean {
            return oldItem.mbid == newItem.mbid
        }

        override fun areContentsTheSame(oldItem: Area, newItem: Area): Boolean {
            return oldItem.name == newItem.name
            //&& oldItem.score == newItem.score
            //&& oldItem.commentCount == newItem.commentCount
        }
    }
}

