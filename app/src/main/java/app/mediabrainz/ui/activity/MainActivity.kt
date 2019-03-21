package app.mediabrainz.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import app.mediabrainz.ui.R

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
