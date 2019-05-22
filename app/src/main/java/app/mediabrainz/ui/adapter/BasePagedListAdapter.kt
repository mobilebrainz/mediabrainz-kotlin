package app.mediabrainz.ui.adapter

import android.view.View
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView


abstract class BasePagedListAdapter<T>(diffCallback: DiffUtil.ItemCallback<T>) :
    PagedListAdapter<T, BasePagedListAdapter.BaseViewHolder<T>>(diffCallback) {

    var holderClickListener: ((T) -> Unit)? = null

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        getItem(position)?.let { item ->
            holder.bindTo(item)
            holderClickListener?.apply {
                holder.itemView.setOnClickListener { invoke(item) }
            }
        }
    }

    abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {

        abstract fun bindTo(item: T)

    }
}

