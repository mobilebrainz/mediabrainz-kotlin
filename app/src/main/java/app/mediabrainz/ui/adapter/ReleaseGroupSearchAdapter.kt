package app.mediabrainz.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DiffUtil
import app.mediabrainz.api.core.getStringFromList
import app.mediabrainz.domain.model.ReleaseGroup
import app.mediabrainz.domain.model.getFrontCoverArtImage
import app.mediabrainz.domain.repository.Resource.Status.*
import app.mediabrainz.ui.R
import app.mediabrainz.ui.extension.StringMapper
import app.mediabrainz.ui.extension.show
import app.mediabrainz.ui.viewmodel.ReleaseGroupCoverArtViewModel


class ReleaseGroupSearchAdapter(private val fragment: Fragment) :
    BasePagedListAdapter<ReleaseGroup>(DiffUtilCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder.create(parent, fragment)

    class ViewHolder(itemView: View, private val fragment: Fragment) : BaseViewHolder<ReleaseGroup>(itemView) {

        companion object {
            private const val VIEW_HOLDER_LAYOUT = R.layout.release_group_search_layout

            fun create(parent: ViewGroup, fragment: Fragment): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(VIEW_HOLDER_LAYOUT, parent, false)
                return ViewHolder(view, fragment)
            }
        }

        private val coverartView: ImageView = itemView.findViewById(R.id.coverartView)
        private val coverartLoadingView: ProgressBar = itemView.findViewById(R.id.coverartLoadingView)
        private val releaseNameView: TextView = itemView.findViewById(R.id.releaseNameView)
        private val releaseTypeView: TextView = itemView.findViewById(R.id.releaseTypeView)
        private val artistNameView: TextView = itemView.findViewById(R.id.artistNameView)
        private val tagsView: TextView = itemView.findViewById(R.id.tagsView)

        override fun bindTo(item: ReleaseGroup) {
            with(item) {
                releaseNameView.text = name
                tagsView.text = getStringFromList(tags, ", ")
                if (artistCredits.isNotEmpty()) {
                    val artist = artistCredits[0].artist
                    artistNameView.text = artist.name
                    if (tags.isEmpty()) {
                        //tagsView.text = artist.disambigution
                    }
                }
                releaseTypeView.text = StringMapper.mapReleaseGroupOneType(item)
                initCoverArt(item.mbid)
            }
        }

        private fun initCoverArt(mbid: String) {
            val vm = ViewModelProviders.of(fragment).get(mbid, ReleaseGroupCoverArtViewModel::class.java)
            vm.result.observe(fragment, Observer {
                it?.apply {
                    when (status) {
                        LOADING -> showImageProgress(true)
                        SUCCESS -> {
                            val coverArt = if (data != null) getFrontCoverArtImage(data!!) else ""
                            if (coverArt.isNotEmpty()) {
                                coverartView.show(coverArt, { showImageProgress(false) }, { showError() })
                            } else showError()
                        }
                        ERROR -> showError()
                    }
                }
            })
            vm.fetch(mbid)
        }

        private fun showImageProgress(show: Boolean) {
            coverartView.visibility = if (show) INVISIBLE else VISIBLE
            coverartLoadingView.visibility = if (show) VISIBLE else INVISIBLE
        }

        private fun showError() {
            showImageProgress(false)
            coverartView.setImageResource(R.drawable.ic_album_24_dark)
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

