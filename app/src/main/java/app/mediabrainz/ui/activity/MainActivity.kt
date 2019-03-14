package app.mediabrainz.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import app.mediabrainz.domain.repository.Resource
import app.mediabrainz.ui.R
import app.mediabrainz.ui.viewmodel.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val vm = ViewModelProviders.of(this).get(EventSearchViewModel::class.java)
        vm.eventsResource.observe(this, Observer { resource ->
            when (resource.status) {
                Resource.Status.LOADING -> Log.i("", "")
                Resource.Status.SUCCESS -> {
                    val data = resource.data
                    Log.i("", "")
                }
                Resource.Status.ERROR -> Log.i("", "")
            }
        })
        vm.searchEvent("London", 25, 0)
    }
}
