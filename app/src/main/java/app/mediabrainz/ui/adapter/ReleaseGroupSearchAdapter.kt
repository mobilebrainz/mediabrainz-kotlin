package app.mediabrainz.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import app.mediabrainz.api.core.getStringFromList
import app.mediabrainz.domain.model.ReleaseGroup
import app.mediabrainz.ui.R
import app.mediabrainz.ui.extension.StringMapper
import kotlinx.android.synthetic.main.release_group_search_layout.view.*


class ReleaseGroupSearchAdapter : BasePagedListAdapter<ReleaseGroup>(DiffUtilCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder.create(parent)

    class ViewHolder(itemView: View) : BaseViewHolder<ReleaseGroup>(itemView) {

        companion object {
            const val VIEW_HOLDER_LAYOUT = R.layout.release_group_search_layout

            fun create(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(VIEW_HOLDER_LAYOUT, parent, false)
                return ViewHolder(view)
            }
        }

        private val coverartView = itemView.coverartView
        private val coverartLoadingView = itemView.coverartLoadingView
        private val releaseNameView = itemView.releaseNameView
        private val releaseTypeView = itemView.releaseTypeView
        private val artistNameView = itemView.artistNameView
        private val tagsView = itemView.tagsView

        override fun bindTo(item: ReleaseGroup) {
            with(item) {
                releaseNameView.text = name
                if (artistCredits.isNotEmpty()) {
                    val artist = artistCredits[0].artist
                    artistNameView.text = artist.name
                    //tagsView.text =
                    //    if (tags.isNotEmpty()) getStringFromList(tags, ", ")
                    //    else artist.disambigution

                }
                releaseTypeView.text = StringMapper.mapReleaseGroupOneType(item)
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

