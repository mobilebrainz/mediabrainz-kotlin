package app.mediabrainz.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import app.mediabrainz.domain.repository.Resource.Status.*
import app.mediabrainz.ui.R
import app.mediabrainz.ui.viewmodel.lookupRepository.ReleaseGroupLookupViewModel


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val vm = ViewModelProviders.of(this).get(ReleaseGroupLookupViewModel::class.java)
        vm.result.observe(this, Observer {
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
        vm.authLookup("b00fbf7c-ebaf-3ec0-91d6-5eaad124d58f")


    }
}
