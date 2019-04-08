package app.mediabrainz.ui.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.annotation.MainThread
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import com.google.android.material.snackbar.Snackbar


abstract class BaseFragment : Fragment() {

    private var errorSnackbar: Snackbar? = null
    private var infoSnackbar: Snackbar? = null

    @MainThread
    protected fun showInfoSnackbar(@StringRes resId: Int) {
        view?.let {
            infoSnackbar = Snackbar.make(it, resId, Snackbar.LENGTH_LONG)
            infoSnackbar?.show()
        }
    }

    @MainThread
    protected fun showErrorSnackbar(
        @StringRes messageResId: Int,
        @StringRes actionResId: Int,
        listener: View.OnClickListener
    ) {
        view?.let {
            errorSnackbar = Snackbar.make(it, messageResId, Snackbar.LENGTH_INDEFINITE)
            errorSnackbar?.setAction(actionResId, listener)?.show()
        }
    }

    protected fun dismissErrorSnackbar() {
        errorSnackbar?.apply {
            if (isShown) dismiss()
        }
    }

    protected fun dismissInfoSnackbar() {
        infoSnackbar?.apply {
            if (isShown) dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        dismissErrorSnackbar()
        dismissInfoSnackbar()
    }

    protected fun <T : ViewModel> getViewModel(modelClass: Class<T>) =
        ViewModelProviders.of(this).get(modelClass)

    protected fun <T : ViewModel> getActivityViewModel(modelClass: Class<T>) =
        ViewModelProviders.of(activity!!).get(modelClass)

    protected fun inflate(@LayoutRes layoutRes: Int, container: ViewGroup?) =
        LayoutInflater.from(context).inflate(layoutRes, container, false)

    protected fun setSubtitle(subtitle: String) {
        activity?.let {
            (it as AppCompatActivity).supportActionBar?.apply {
                this.subtitle = subtitle
            }
        }
    }

    protected fun setTitle(title: String) {
        activity?.let {
            (it as AppCompatActivity).supportActionBar?.apply {
                this.title = title
            }
        }
    }

    protected fun navigate(directions: NavDirections) {
        view?.let {
            Navigation.findNavController(it).navigate(directions)
        }
    }

    protected fun navigate(@IdRes resId: Int) {
        view?.let {
            Navigation.findNavController(it).navigate(resId)
        }
    }

}