package app.mediabrainz.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import app.mediabrainz.domain.model.ReleaseGroup
import app.mediabrainz.domain.repository.Resource.Status.*
import app.mediabrainz.ui.R
import app.mediabrainz.ui.viewmodel.ReleaseGroupCoverArtViewModel
import kotlinx.android.synthetic.main.test_adapter_row.view.*


class PagedAdapterWithCoverArts(private val fragment: Fragment) :
    PagedListAdapter<ReleaseGroup, PagedAdapterWithCoverArts.BrowseViewHolder>(DiffUtilCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrowseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.test_adapter_row, parent, false)
        return BrowseViewHolder(view, fragment)
    }

    override fun onBindViewHolder(holder: BrowseViewHolder, position: Int) {
        getItem(position)?.let { holder.bindTo(it) }
    }

    class BrowseViewHolder(itemView: View, private val fragment: Fragment) : RecyclerView.ViewHolder(itemView) {

        fun bindTo(item: ReleaseGroup) {
            with(item) {
                initCoverArt(mbid)
                itemView.nameView.text = name
            }
        }

        private fun initCoverArt(mbid: String) {
            val vm = ViewModelProviders.of(fragment).get(mbid, ReleaseGroupCoverArtViewModel::class.java)
            vm.result.observe(fragment, Observer {
                it?.apply {
                    when (status) {
                        LOADING -> Log.i("", "")
                        SUCCESS -> {
                            data?.apply {
                                Log.i("", "")
                            }
                        }
                        ERROR -> Log.i("", "")
                    }
                }
            })
            vm.fetch(mbid)
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

