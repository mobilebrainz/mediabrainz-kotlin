package app.mediabrainz.ui.core.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView


abstract class BaseRecyclerViewAdapter<T>(val items: List<T>) :
    RecyclerView.Adapter<BaseRecyclerViewAdapter.BaseViewHolder<T>>()
{

    var holderClickListener: ((T) -> Unit)? = null

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        items[position]?.let { item ->
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