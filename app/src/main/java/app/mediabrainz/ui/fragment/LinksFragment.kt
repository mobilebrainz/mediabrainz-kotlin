package app.mediabrainz.ui.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.mediabrainz.ui.R
import app.mediabrainz.ui.adapter.LinkAdapter
import app.mediabrainz.ui.viewmodel.event.LinksEvent
import app.mediabrainz.ui.viewmodel.fragment.LinksFragmentVM


class LinksFragment : BaseFragment() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflate(R.layout.nested_recycler_fragment, container)
        recyclerView = view.findViewById(R.id.recyclerView)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        arguments?.let {
            val args = LinksFragmentArgs.fromBundle(it)
            setSubtitle(args.subTitle ?: "")

            val linksVM = getViewModel(LinksFragmentVM::class.java)
            getActivityViewModel(LinksEvent::class.java).urlRelations.observe(this, Observer {
                if (it != null) linksVM.urlRelations.value = it
            })

            linksVM.urlRelations.observe(this, Observer {
                if (it != null) {
                    val adapter = LinkAdapter(it)
                    adapter.holderClickListener = {
                        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(it.relation.resource)))
                    }
                    recyclerView.layoutManager = LinearLayoutManager(context)
                    recyclerView.setHasFixedSize(true)
                    recyclerView.adapter = adapter
                }
            })
        }
    }

}
