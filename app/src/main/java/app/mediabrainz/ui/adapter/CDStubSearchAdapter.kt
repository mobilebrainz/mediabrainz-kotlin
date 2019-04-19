package app.mediabrainz.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import app.mediabrainz.domain.model.CDStub
import app.mediabrainz.ui.R
import app.mediabrainz.ui.extension.findViewById


class CDStubSearchAdapter : BasePagedListAdapter<CDStub>(DiffUtilCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.test_adapter_row, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(itemView: View) : BaseViewHolder<CDStub>(itemView) {

        val nameView: TextView = findViewById(R.id.nameView)

        override fun bindTo(item: CDStub) {
            with(item) {
                nameView.text = name
            }
        }
    }

    class DiffUtilCallBack : DiffUtil.ItemCallback<CDStub>() {
        override fun areItemsTheSame(oldItem: CDStub, newItem: CDStub): Boolean {
            return oldItem.mbid == newItem.mbid
        }

        override fun areContentsTheSame(oldItem: CDStub, newItem: CDStub): Boolean {
            return oldItem.name == newItem.name
            //&& oldItem.score == newItem.score
            //&& oldItem.commentCount == newItem.commentCount
        }
    }
}

