package app.mediabrainz.ui.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.menu.MenuBuilder
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import app.mediabrainz.ui.R
import app.mediabrainz.ui.extension.handleBottomSheetBehavior
import com.google.android.material.navigation.NavigationView
import java.lang.ref.WeakReference


class MainActivity : AppCompatActivity(),
    NavigationView.OnNavigationItemSelectedListener {

    private lateinit var coordinatorLayout: CoordinatorLayout
    private lateinit var drawer: DrawerLayout
    private lateinit var navController: NavController
    private lateinit var navigationView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        setSupportActionBar(findViewById(R.id.toolbar))

        coordinatorLayout = findViewById(R.id.coordinatorLayout)

        drawer = findViewById(R.id.drawer)
        navController = findNavController(R.id.navHostView)
        setupActionBarWithNavController(navController, drawer)
        navigationView = findViewById(R.id.navigationView)
        navigationView.setNavigationItemSelectedListener(this)

        val weakReference = WeakReference(navigationView)
        navController.addOnDestinationChangedListener(object : NavController.OnDestinationChangedListener {
            override fun onDestinationChanged(
                controller: NavController,
                destination: NavDestination,
                arguments: Bundle?
            ) {
                val view = weakReference.get()
                if (view == null) {
                    navController.removeOnDestinationChangedListener(this)
                } else {
                    //todo: при навигации в дровере иногда неправильно выделяет айтемы, но надо сделать чтобы правильно выделялись айтемы при бекпрессе
                    //NavigationUIExtension.checkNavViewMenuItem(view, destination);
                    hideLogNavItems()
                }
            }
        })

        /*
        val fab = findViewById(R.id.fabView)
        fab.setOnClickListener({ view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).showArtists()
        })
        */
    }

    private fun hideLogNavItems() {
        //navigationView.menu.findItem(R.id.loginFragment).setVisible(!oauth.hasAccount())
        //navigationView.menu.findItem(R.id.logoutAction).setVisible(oauth.hasAccount())
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_options_menu, menu)
        if (menu is MenuBuilder) {
            menu.setOptionalIconsVisible(true)
        }
        return true
    }

    override fun onOptionsItemSelected(menuItem: MenuItem): Boolean {
        var handled = true
        when (menuItem.itemId) {
            android.R.id.home -> {
                //drawer.openDrawer(GravityCompat.START);
                val currentDestination = navController.currentDestination
                if (currentDestination != null && currentDestination.id != R.id.startFragment) {
                    onBackPressed()
                } else {
                    drawer.openDrawer(GravityCompat.START)
                }
            }
            else -> handled = NavigationUI.onNavDestinationSelected(menuItem, navController)
        }
        return handled
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        var handled = true
        when (menuItem.itemId) {
            /*
            R.id.feedbackAction -> sendEmail()
            R.id.scanBarcodeAction -> IntentIntegrator.initiateScan(
                this, getString(R.string.zx_title), getString(R.string.zx_message),
                getString(R.string.zx_pos), getString(R.string.zx_neg), IntentIntegrator.PRODUCT_CODE_TYPES
            )
            R.id.logoutAction -> {
                //todo: add confirm dialog?
                oauth.logOut()
                navController.navigate(R.id.startFragment)
            }
            */
            else -> handled = NavigationUI.onNavDestinationSelected(menuItem, navController)
        }
        if (handled) {
            navigationView.handleBottomSheetBehavior()
        }
        drawer.closeDrawer(GravityCompat.START)
        return handled
    }


}
