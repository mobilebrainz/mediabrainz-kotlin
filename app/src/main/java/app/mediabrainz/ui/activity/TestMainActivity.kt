package app.mediabrainz.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import app.mediabrainz.domain.repository.Resource.Status.*
import app.mediabrainz.ui.R
import app.mediabrainz.ui.viewmodel.lookupRepository.ReleaseGroupLookupViewModel


class TestMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.test_activity_main)


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

        /*
        //ArtistLookupViewModel:
        vm.authLookup("c5119d8a-ffee-48bc-99c3-f323bdf16d82", true)
        vm.authLookup("e64bfb93-5f24-4a81-89d3-6cc00c18792a", true)
        vm.authLookup("10bf95b6-30e3-44f1-817f-45762cdc0de0", true)
        vm.authLookup("6c18e442-1c51-4aca-bae7-413ae2a86731", true)
        vm.authLookup("a5e23e6c-4a59-4ee9-a982-34d2059439a3", true)
        vm.authLookup("eace2373-31c8-4aba-9a5c-7bce22dd140a", true)
        vm.authLookup("3954ea37-d90c-49d6-8b3a-240bf4d48e51", true)
        vm.authLookup("2aafac4c-273d-4fd8-a611-392003e06ec3", true)
        vm.authLookup("f3a1d603-4167-453b-acf5-fd9ae5cf25ae", true)
        vm.authLookup("48805861-aef1-479e-8a48-1389e8d0dcf1", true)
        vm.authLookup("8c19f483-84a8-4157-9509-b92077624dcf", true)
        vm.authLookup("7f39dfbf-1074-445e-98c8-a5233909ddfb", true)
        */

    }
}
