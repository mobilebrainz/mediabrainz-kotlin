package app.mediabrainz.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import app.mediabrainz.domain.OAuthManager.OAUTH_REDIRECT_URI
import app.mediabrainz.domain.OAuthManager.OAUTH_RESPONSE_TYPE
import app.mediabrainz.domain.OAuthManager
import app.mediabrainz.domain.repository.Resource.Status.*
import app.mediabrainz.ui.R
import app.mediabrainz.ui.viewmodel.OAuthViewModel


class TestLoginActivity : AppCompatActivity() {

    lateinit var vm: OAuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_test_activity)

        vm = ViewModelProviders.of(this).get(OAuthViewModel::class.java)
        vm.accessToken.observe(this, Observer {
            it?.apply {
                when (status) {
                    LOADING -> Log.i("", "")
                    SUCCESS -> {
                        data?.apply {
                            OAuthManager.accessToken = this
                            Log.i("", "")
                        }
                    }
                    ERROR -> Log.i("", "")
                }
            }
        })

        findViewById<Button>(R.id.login_btn).setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, vm.getAuthorizationUri()))
        }
    }

    override fun onResume() {
        super.onResume()
        intent.data?.apply {
            if (toString().startsWith(OAUTH_REDIRECT_URI)) {
                getQueryParameter(OAUTH_RESPONSE_TYPE)?.let { vm.requestToken(it) }
            }
        }
    }

}
