package app.mediabrainz.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import app.mediabrainz.domain.model.Instrument
import app.mediabrainz.ui.R
import app.mediabrainz.ui.extension.findViewById


class InstrumentSearchAdapter : BasePagedListAdapter<Instrument>(DiffUtilCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.test_adapter_row, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(itemView: View) : BaseViewHolder<Instrument>(itemView) {

        val nameView: TextView = findViewById(R.id.nameView)

        override fun bindTo(item: Instrument) {
            with(item) {
                nameView.text = name
            }
        }
    }

    class DiffUtilCallBack : DiffUtil.ItemCallback<Instrument>() {
        override fun areItemsTheSame(oldItem: Instrument, newItem: Instrument): Boolean {
            return oldItem.mbid == newItem.mbid
        }

        override fun areContentsTheSame(oldItem: Instrument, newItem: Instrument): Boolean {
            return oldItem.name == newItem.name
            //&& oldItem.score == newItem.score
            //&& oldItem.commentCount == newItem.commentCount
        }
    }
}

