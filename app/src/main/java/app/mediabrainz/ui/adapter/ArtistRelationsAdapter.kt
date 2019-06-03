package app.mediabrainz.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import app.mediabrainz.api.core.getStringFromList
import app.mediabrainz.domain.model.Artist
import app.mediabrainz.domain.model.Relation
import app.mediabrainz.ui.R
import app.mediabrainz.ui.core.adapter.BaseRecyclerViewAdapter
import app.mediabrainz.ui.extension.findViewById


class ArtistRelationsAdapter(artistRels: List<Relation<Artist>>) :
    BaseRecyclerViewAdapter<Relation<Artist>>(artistRels) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistRelationsViewHolder {
        return ArtistRelationsViewHolder.create(parent)
    }

    class ArtistRelationsViewHolder(v: View) : BaseRecyclerViewAdapter.BaseViewHolder<Relation<Artist>>(v) {

        companion object {
            private const val VIEW_HOLDER_LAYOUT = R.layout.artist_relations_layout

            fun create(parent: ViewGroup): ArtistRelationsViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(VIEW_HOLDER_LAYOUT, parent, false)
                return ArtistRelationsViewHolder(view)
            }
        }

        private val artistView: TextView = findViewById(R.id.artistView)
        private val typeView: TextView = findViewById(R.id.typeView)
        private val beginView: TextView = findViewById(R.id.beginView)
        private val endView: TextView = findViewById(R.id.endView)

        override fun bindTo(item: Relation<Artist>) {
            with(item) {
                artistView.text = relation.name
                var b = if (begin.isEmpty()) "..." else begin
                var e = if (end.isEmpty()) "..." else end
                if (b == "..." && e == "...") {
                    b = ""
                    e = ""
                }
                beginView.text = b
                endView.text = e

                typeView.text = getStringFromList(attributes, ", ")
            }
        }
    }

}