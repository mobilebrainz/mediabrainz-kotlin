package app.mediabrainz.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import app.mediabrainz.domain.model.Artist
import app.mediabrainz.ui.R
import kotlinx.android.synthetic.main.artist_search_adapter_row.view.*


class ArtistSearchAdapter :
    PagedListAdapter<Artist, ArtistSearchAdapter.ArtistSearchViewHolder>(DiffUtilCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistSearchViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.artist_search_adapter_row, parent, false)
        return ArtistSearchViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArtistSearchViewHolder, position: Int) {
        getItem(position)?.let { holder.bindTo(it) }
    }

    class ArtistSearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val nameView = itemView.nameView

        fun bindTo(artist: Artist) {
            with(artist) {
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

