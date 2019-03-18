package app.mediabrainz.ui.fragment

import android.view.View
import androidx.annotation.MainThread
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar


abstract class BaseFragment: Fragment() {

    protected var errorSnackbar: Snackbar? = null
    protected var infoSnackbar: Snackbar? = null

    @MainThread
    protected fun showInfoSnackbar(@StringRes resId: Int) {
        if (view != null) {
            infoSnackbar = Snackbar.make(view!!, resId, Snackbar.LENGTH_LONG)
            infoSnackbar?.show()
        }
    }

    @MainThread
    protected fun showErrorSnackbar(@StringRes messageResId: Int, @StringRes actionResId: Int, listener: View.OnClickListener) {
        if (view != null) {
            errorSnackbar = Snackbar.make(view!!, messageResId, Snackbar.LENGTH_INDEFINITE)
            errorSnackbar?.setAction(actionResId, listener)?.show()
        }
    }

    protected fun dismissErrorSnackbar() {
        errorSnackbar?.let { if (it.isShown) it.dismiss() }
    }

    protected fun dismissInfoSnackbar() {
        infoSnackbar?.let { if (it.isShown) it.dismiss() }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        dismissErrorSnackbar()
        dismissInfoSnackbar()
    }
}