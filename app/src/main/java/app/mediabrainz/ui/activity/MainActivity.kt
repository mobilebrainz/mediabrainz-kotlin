package app.mediabrainz.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import app.mediabrainz.domain.repository.Resource
import app.mediabrainz.ui.R
import app.mediabrainz.ui.viewmodel.AnnotationSearchViewModel
import app.mediabrainz.ui.viewmodel.ArtistSearchViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val vm = ViewModelProviders.of(this).get(AnnotationSearchViewModel::class.java)
        vm.annotationsResource.observe(this, Observer { resource ->
            when (resource.status) {
                Resource.Status.LOADING -> Log.i("", "")
                Resource.Status.SUCCESS -> {
                    val data = resource.data
                    Log.i("", "")
                }
                Resource.Status.ERROR -> Log.i("", "")
            }
        })
        vm.fetchAnnotations("River", 25, 0)
        /*
        vm.fetchArtists("Riversid", 25, 0)
        vm.fetchArtists("Riversi", 25, 0)
        vm.fetchArtists("Rivers", 25, 0)
        vm.fetchArtists("River", 25, 0)
        vm.fetchArtists("Rive", 25, 0)
        vm.fetchArtists("Riv", 25, 0)
        vm.fetchArtists("R", 25, 0)
        vm.fetchArtists("Riverside", 25, 0)
        vm.fetchArtists("Riversid", 25, 0)
        vm.fetchArtists("Riversi", 25, 0)
        vm.fetchArtists("Rivers", 25, 0)
        vm.fetchArtists("River", 25, 0)
        vm.fetchArtists("Rive", 25, 0)
        vm.fetchArtists("Riv", 25, 0)
        vm.fetchArtists("R", 25, 0)
        vm.fetchArtists("River", 25, 0)
        vm.fetchArtists("Rive", 25, 0)
        vm.fetchArtists("Riv", 25, 0)
        vm.fetchArtists("R", 25, 0)
        */
    }
}
