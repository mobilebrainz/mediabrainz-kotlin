package app.mediabrainz.ui.adapter

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
import app.mediabrainz.domain.model.Release
import app.mediabrainz.domain.model.ReleaseGroup
import app.mediabrainz.domain.model.getFrontCoverArtImage
import app.mediabrainz.domain.repository.Resource.Status.*
import app.mediabrainz.ui.R
import app.mediabrainz.ui.extension.findViewById
import app.mediabrainz.ui.extension.show
import app.mediabrainz.ui.preference.GlobalPreferences
import app.mediabrainz.ui.viewmodel.ReleaseCoverArtViewModel
import app.mediabrainz.ui.viewmodel.ReleaseGroupCoverArtViewModel


class ReleaseAdapter(private val fragment: Fragment) :
    BasePagedListAdapter<Release>(DiffUtilCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder.create(parent, fragment)

    class ViewHolder(itemView: View, private val fragment: Fragment) : BaseViewHolder<Release>(itemView) {

        companion object {
            private const val VIEW_HOLDER_LAYOUT = R.layout.release_layout

            fun create(parent: ViewGroup, fragment: Fragment): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(VIEW_HOLDER_LAYOUT, parent, false)
                return ViewHolder(view, fragment)
            }
        }

        private val coverartView: ImageView = findViewById(R.id.coverartView);
        private val coverartLoadingView: ProgressBar = findViewById(R.id.coverartLoadingView);
        private val dateView: TextView = findViewById(R.id.dateView);
        private val releaseNameView: TextView = findViewById(R.id.releaseNameView);
        private val countryLabelView: TextView = findViewById(R.id.countryLabelView);
        private val formatView: TextView = findViewById(R.id.formatView);
        private val statusView: TextView = findViewById(R.id.statusView);
        private val catalogView: TextView = findViewById(R.id.catalogView);
        private val barcodeView: TextView = findViewById(R.id.barcodeView);

        override fun bindTo(item: Release) {
            with(item) {
                //dateView.text = date
                releaseNameView.text = name

                if (GlobalPreferences.isLoadImagesEnabled()) {
                    initCoverArt(mbid)
                } else {
                    coverartView.visibility = VISIBLE
                }
            }
        }

        private fun initCoverArt(mbid: String) {
            val vm = ViewModelProviders.of(fragment).get(mbid, ReleaseCoverArtViewModel::class.java)
            vm.result.observe(fragment, Observer {
                it?.apply {
                    when (status) {
                        LOADING -> showImageProgress(true)
                        SUCCESS -> {
                            val coverArt = if (data != null) getFrontCoverArtImage(data) else ""
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

    class DiffUtilCallBack : DiffUtil.ItemCallback<Release>() {
        override fun areItemsTheSame(oldItem: Release, newItem: Release): Boolean {
            return oldItem.mbid == newItem.mbid
        }

        override fun areContentsTheSame(oldItem: Release, newItem: Release): Boolean {
            return oldItem.name == newItem.name
            //&& oldItem.score == newItem.score
        }
    }
}

