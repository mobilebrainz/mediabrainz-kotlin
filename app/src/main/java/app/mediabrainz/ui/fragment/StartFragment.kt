package app.mediabrainz.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.mediabrainz.ui.R


class StartFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflate(R.layout.start_fragment, container)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setSubtitle("")

        /*
        val vm = ViewModelProviders.of(this).get(ReleaseGroupLookupViewModel::class.java)
        vm.result.observe(this, Observer {
            it?.apply {
                when (status) {
                    Resource.Status.LOADING -> Log.i("", "")
                    Resource.Status.SUCCESS -> {
                        data?.apply {
                            Log.i("", "")
                        }
                    }
                    Resource.Status.ERROR -> Log.i("", "")
                }
            }
        })
        vm.authLookup("b00fbf7c-ebaf-3ec0-91d6-5eaad124d58f")
        */
    }

}