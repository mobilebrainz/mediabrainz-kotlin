package app.mediabrainz.ui.adapter.pager

import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DiffUtil
import app.mediabrainz.domain.model.ReleaseGroup
import app.mediabrainz.domain.model.getFrontCoverArtImage
import app.mediabrainz.domain.repository.Resource.Status.*
import app.mediabrainz.ui.R
import app.mediabrainz.ui.adapter.BasePagedListAdapter
import app.mediabrainz.ui.extension.findViewById
import app.mediabrainz.ui.extension.show
import app.mediabrainz.ui.preference.GlobalPreferences
import app.mediabrainz.ui.viewmodel.ReleaseGroupCoverArtViewModel


class ReleaseGroupAdapter(private val fragment: Fragment) :
    BasePagedListAdapter<ReleaseGroup>(DiffUtilCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder.create(parent, fragment)

    class ViewHolder(itemView: View, private val fragment: Fragment) : BaseViewHolder<ReleaseGroup>(itemView) {

        companion object {
            private const val VIEW_HOLDER_LAYOUT = R.layout.release_group_layout

            fun create(parent: ViewGroup, fragment: Fragment): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(VIEW_HOLDER_LAYOUT, parent, false)
                return ViewHolder(view, fragment)
            }
        }

        private val releaseImageView: ImageView = findViewById(R.id.releaseImageView)
        private val progressView: ProgressBar = findViewById(R.id.progressView)
        private val releaseNameView: TextView = findViewById(R.id.releaseNameView)
        private val releaseTypeYearView: TextView = findViewById(R.id.releaseTypeYearView)

        private val ratingContainerView: LinearLayout = findViewById(R.id.ratingContainerView)
        private val userRatingView: RatingBar = findViewById(R.id.userRatingView)
        private val ratingView: TextView = findViewById(R.id.ratingView)

        override fun bindTo(item: ReleaseGroup) {
            with(item) {
                releaseNameView.text = name

                val year = if (getYear() != 0) getYear().toString() else ""
                val type = getAllTypes()
                releaseTypeYearView.text = "$year ($type)"

                setAllRating(item)
                setUserRating(item)

                if (GlobalPreferences.isLoadImagesEnabled()) {
                    initCoverArt(item.mbid)
                } else {
                    releaseImageView.visibility = VISIBLE
                }
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
                                releaseImageView.show(coverArt, { showImageProgress(false) }, { showError() })
                            } else showError()
                        }
                        ERROR -> showError()
                    }
                }
            })
            vm.fetch(mbid)
        }

        private fun showImageProgress(show: Boolean) {
            releaseImageView.visibility = if (show) INVISIBLE else VISIBLE
            progressView.visibility = if (show) VISIBLE else INVISIBLE
        }

        private fun showError() {
            showImageProgress(false)
            releaseImageView.setImageResource(R.drawable.ic_album_24)
        }

        private fun setAllRating(releaseGroup: ReleaseGroup) {
            releaseGroup.rating?.apply {
                ratingView.text =
                    if (value != 0.0f) itemView.context.getString(R.string.rating_text, value, votesCount)
                    else itemView.context.getString(R.string.rating_text, 0.0, 0)
            }
        }

        private fun setUserRating(releaseGroup: ReleaseGroup) {
            releaseGroup.userRating?.apply {
                userRatingView.rating = value
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

