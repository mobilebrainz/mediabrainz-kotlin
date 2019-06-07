package app.mediabrainz.ui.adapter

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import app.mediabrainz.domain.model.Tag
import app.mediabrainz.ui.R
import app.mediabrainz.ui.core.adapter.BaseRecyclerViewAdapter
import app.mediabrainz.ui.extension.findViewById


class TagAdapter(tags: List<Tag>, val userTags: List<Tag>):
    BaseRecyclerViewAdapter<Tag>(tags) {

    var onVoteTagListener: (Int) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        TagViewHolder.create(parent)

    override fun onBindViewHolder(holder: BaseViewHolder<Tag>, position: Int) {
        (holder as TagViewHolder).setOnVoteTagListener(onVoteTagListener)
        for (userTag in userTags) {
            if (userTag == items[position]) {
                holder.isVotted = true
                break
            }
        }
        super.onBindViewHolder(holder, position)
    }

    class TagViewHolder(v: View) : BaseRecyclerViewAdapter.BaseViewHolder<Tag>(v) {

        companion object {
            private const val VIEW_HOLDER_LAYOUT = R.layout.tag_layout

            fun create(parent: ViewGroup): TagViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(VIEW_HOLDER_LAYOUT, parent, false)
                return TagViewHolder(view)
            }
        }

        var isVotted = false

        private val tagNameView: TextView = findViewById(R.id.tagNameView)
        private val votesCountView: TextView = findViewById(R.id.votesCountView)
        private val voteButton: ImageView = findViewById(R.id.voteButton)

        override fun bindTo(item: Tag) {
            tagNameView.text = item.name
            votesCountView.text = item.count.toString()
            if (isVotted) {
                voteButton.imageTintList =
                    ColorStateList.valueOf(ContextCompat.getColor(itemView.context, R.color.colorAccent))
            }
        }

        fun setOnVoteTagListener(listener: (Int) -> Unit) {
            voteButton.setOnClickListener { listener.invoke(adapterPosition) }
        }
    }
}