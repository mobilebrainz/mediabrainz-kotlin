package app.mediabrainz.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DiffUtil
import app.mediabrainz.api.core.getStringFromList
import app.mediabrainz.domain.model.Artist
import app.mediabrainz.ui.R
import app.mediabrainz.ui.extension.findViewById
import app.mediabrainz.ui.extension.getString
import app.mediabrainz.ui.extension.setEmptyText
import app.mediabrainz.ui.extension.setEmptyVisibility


class ArtistSearchAdapter(private val fragment: Fragment) :
    BasePagedListAdapter<Artist>(DiffUtilCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder.create(parent, fragment)

    class ViewHolder(itemView: View, private val fragment: Fragment) : BaseViewHolder<Artist>(itemView) {

        companion object {
            private const val VIEW_HOLDER_LAYOUT = R.layout.artist_search_layout

            fun create(parent: ViewGroup, fragment: Fragment): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(VIEW_HOLDER_LAYOUT, parent, false)
                return ViewHolder(view, fragment)
            }
        }

        private val imageView: ImageView = findViewById(R.id.imageView)
        private val imageProgressView: ProgressBar = findViewById(R.id.imageProgressView)
        private val artistNameView: TextView = findViewById(R.id.artistNameView)
        private val areaView: TextView = findViewById(R.id.areaView)
        private val disambiguationView: TextView = findViewById(R.id.disambiguationView)
        private val tagsView: TextView = findViewById(R.id.tagsView)
        private val artistTypeView: TextView = findViewById(R.id.artistTypeView)

        override fun bindTo(item: Artist) {
            with(item) {
                artistNameView.text = name
                artistTypeView.text = getString(type.id)

                var areaStr = area?.name ?: ""
                var foundedstr = lifeSpan.begin
                if (lifeSpan.end.isNotEmpty()) {
                    foundedstr += " - " + lifeSpan.end
                }
                val separator = if (areaStr.isNotEmpty() && foundedstr.isNotEmpty()) ", " else ""
                areaStr += separator + foundedstr

                areaView.setEmptyText(areaStr)
                disambiguationView.setEmptyText(disambiguation)
                tagsView.setEmptyText(getStringFromList(tags, ", "))
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

