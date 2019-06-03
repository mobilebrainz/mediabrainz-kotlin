package app.mediabrainz.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import app.mediabrainz.domain.model.Relation
import app.mediabrainz.domain.model.Url
import app.mediabrainz.ui.R
import app.mediabrainz.ui.core.adapter.BaseRecyclerViewAdapter
import app.mediabrainz.ui.extension.findViewById


class LinkAdapter(urlRels: List<Relation<Url>>) :
    BaseRecyclerViewAdapter<Relation<Url>>(urlRels) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LinkViewHolder {
        return LinkViewHolder.create(parent)
    }

    class LinkViewHolder(v: View) : BaseRecyclerViewAdapter.BaseViewHolder<Relation<Url>>(v) {

        companion object {
            private const val VIEW_HOLDER_LAYOUT = R.layout.link_layout

            fun create(parent: ViewGroup): LinkViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(VIEW_HOLDER_LAYOUT, parent, false)
                return LinkViewHolder(view)
            }
        }

        private val iconView: ImageView = findViewById(R.id.iconView)
        private val typeView: TextView = findViewById(R.id.typeView)
        private val linkView: TextView = findViewById(R.id.linkView)

        override fun bindTo(item: Relation<Url>) {
            with(item) {
                typeView.text = getPrettyType()
                linkView.text = relation.getPrettyUrl()

                type?.let {
                    val t = it.toLowerCase()
                    var iconId = R.drawable.ic_link_24_dark
                    when (t) {
                        "youtube" -> iconId = R.drawable.ic_youtube_24_light
                        "official homepage" -> iconId = R.drawable.ic_home_24_dark
                        "imdb" -> iconId = R.drawable.ic_film_24
                        "fanpage" -> iconId = R.drawable.ic_community_24_dark
                        "online community" -> iconId = R.drawable.ic_community_24_dark
                        "wikipedia" -> iconId = R.drawable.ic_wikipedia_24
                        "lyrics" -> iconId = R.drawable.ic_lyrics_24
                        "download for free" -> iconId = R.drawable.ic_download_24
                        "soundcloud" -> iconId = R.drawable.ic_soundcloud_24
                    }
                    val r = relation.resource
                    when {
                        t.startsWith("streaming") -> iconId = R.drawable.ic_streaming_24
                        t.startsWith("purchase") -> iconId = R.drawable.ic_basket_24
                        r.contains("twitter") -> iconId = R.drawable.ic_twitter_24
                        r.contains("facebook") -> iconId = R.drawable.ic_facebook_24
                        t.contains("discog") -> iconId = R.drawable.ic_album_24_dark
                        r.contains("vimeo") -> iconId = R.drawable.ic_vimeo_24
                    }
                    iconView.setImageResource(iconId)
                }

            }
        }
    }

}