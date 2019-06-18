package app.mediabrainz.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.mediabrainz.api.xml.entity.TagVoteType.*
import app.mediabrainz.domain.model.Tag
import app.mediabrainz.domain.model.TagType
import app.mediabrainz.ui.R
import app.mediabrainz.ui.adapter.TagAdapter
import app.mediabrainz.ui.preference.OAuthPreferences
import app.mediabrainz.ui.viewmodel.activity.TaggedVM
import app.mediabrainz.ui.viewmodel.activity.TaggedVM.*
import java.util.*


class EditTagsTabFragment : BaseFragment() {

    private lateinit var taggedVM: TaggedVM
    private var tab = 0

    private lateinit var recyclerView: RecyclerView

    companion object {
        private val TAGS_TAB = "EditTagsTabFragment.TAGS_TAB"

        fun newInstance(tab: Int): EditTagsTabFragment {
            val args = Bundle()
            args.putInt(TAGS_TAB, tab)
            val fragment = EditTagsTabFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflate(R.layout.nested_recycler_fragment, container)
        recyclerView = view.findViewById(R.id.recyclerView)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        arguments?.let {
            tab = it.getInt(TAGS_TAB)
            taggedVM = getActivityViewModel(TaggedVM::class.java)
            load()
        }
    }

    private fun load() {
        val tags = ArrayList<Tag>()
        val userTags = ArrayList<Tag>()
        taggedVM.tagged?.let {
            when (tab) {
                TagType.TAG.ordinal -> {
                    tags.addAll(it.tags)
                    userTags.addAll(it.userTags)
                }
                TagType.GENRE.ordinal -> {
                    tags.addAll(it.genres)
                    userTags.addAll(it.userGenres)
                }
            }

            if (tags.isNotEmpty()) {
                recyclerView.layoutManager = LinearLayoutManager(context)
                //recyclerView.setItemViewCacheSize(100)
                recyclerView.setHasFixedSize(true)

                val adapter = TagAdapter(tags, userTags)
                adapter.holderClickListener = {

                }
                recyclerView.adapter = adapter

                context?.let {
                    adapter.onVoteTagListener = { pos ->
                        if (OAuthPreferences.isNotEmpty()) {
                            val tag = tags[pos].name
                            val alertDialog = AlertDialog.Builder(it).create()
                            alertDialog.show()
                            alertDialog.window?.apply {
                                setContentView(R.layout.dialog_vote_tag)

                                val voteUpButton = findViewById<ImageView>(R.id.voteUpButton)
                                voteUpButton.setOnClickListener {
                                    alertDialog.dismiss()
                                    taggedVM.postTag.setValue(TagVote(tag, UPVOTE))
                                }

                                val voteWithdrawButton = findViewById<ImageView>(R.id.voteWithdrawButton)
                                voteWithdrawButton.setOnClickListener {
                                    alertDialog.dismiss()
                                    taggedVM.postTag.setValue(TagVote(tag, WITHDRAW))
                                }

                                val voteDownButton = findViewById<ImageView>(R.id.voteDownButton)
                                voteDownButton.setOnClickListener {
                                    alertDialog.dismiss()
                                    taggedVM.postTag.setValue(TagVote(tag, DOWNVOTE))
                                }
                            }
                        } else {
                            //navigate(R.id.action_global_loginFragment)
                        }
                    }
                }
            }
        }
    }

}
