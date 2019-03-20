package app.mediabrainz.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import app.mediabrainz.domain.repository.Resource.Status.*
import app.mediabrainz.ui.R
import app.mediabrainz.ui.viewmodel.UrlSearchViewModel
import app.mediabrainz.ui.viewmodel.WorkSearchViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        /*
        val vm = ViewModelProviders.of(this).get(WorkSearchViewModel::class.java)
        vm.workResource.observe(this, Observer {
            when (it.status) {
                LOADING -> Log.i("", "")
                SUCCESS -> {
                    val data = it.data
                    Log.i("", "")
                }
                ERROR -> Log.i("", "")
            }
        })
        vm.searchWork("black")
        */

    }
}
