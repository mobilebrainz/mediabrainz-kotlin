package app.mediabrainz.ui.extension

import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.navigation.NavigationView

/**
 * From NavigationUI.setupWithNavController(@NonNull final NavigationView navigationView, @NonNull final NavController navController)
 */
fun NavigationView.handleBottomSheetBehavior() {
    val parent = this.parent
    if (parent is DrawerLayout) {
        parent.closeDrawer(this)
    } else {
        findBottomSheetBehavior(this)?.setState(BottomSheetBehavior.STATE_HIDDEN)
    }
}

/**
 * From NavigationUI.findBottomSheetBehavior(@NonNull View view)
 * Walks up the view hierarchy, trying to determine if the given View is contained within a bottom sheet.
 */
private fun findBottomSheetBehavior(view: View): BottomSheetBehavior<*>? {
    val params = view.layoutParams
    if (params !is CoordinatorLayout.LayoutParams) {
        val parent = view.parent
        return if (parent is View) findBottomSheetBehavior(parent as View) else null
    }
    val behavior = params.behavior
    return if (behavior is BottomSheetBehavior<*>) behavior else null
}

