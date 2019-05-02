package app.mediabrainz.ui.extension

import android.app.Activity
import android.content.Context.INPUT_METHOD_SERVICE
import android.view.WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders


fun Activity.hideKeyboard() {
    window.setSoftInputMode(SOFT_INPUT_STATE_HIDDEN)
    currentFocus?.apply {
        val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
    }
}

fun <T : ViewModel> FragmentActivity.getViewModel(modelClass: Class<T>): T =
    ViewModelProviders.of(this).get(modelClass)

