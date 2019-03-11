package app.mediabrainz.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import app.mediabrainz.api.service.ApiServiceProvider
import app.mediabrainz.ui.viewmodel.SearchViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*
        val service = ApiServiceProvider.createArtistSearchService()
        CoroutineScope(Dispatchers.IO).launch {
            val request = service.search("Riverside")
            try {
                val response = request.await()
                Log.i("", "")
                // Do something with the response.body()
            } catch (e: HttpException) {
                Log.i("", "")
            } catch (e: Throwable) {
                Log.i("", "")
            }
        }
        */

        val vm = ViewModelProviders.of(this).get(SearchViewModel::class.java)

        vm.fetchArtists("Riverside", 25, 0).observe(this, Observer { resource ->
            Log.i("", "")
        })

    }
}
