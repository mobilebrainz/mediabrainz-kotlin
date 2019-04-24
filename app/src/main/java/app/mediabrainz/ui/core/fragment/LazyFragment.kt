package app.mediabrainz.ui.core.fragment

import app.mediabrainz.ui.fragment.BaseFragment


abstract class LazyFragment : BaseFragment() {

    var isLoaded: Boolean = false

    protected abstract fun lazyLoad()

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser && isResumed && !isLoaded) {
            lazyLoad()
            isLoaded = true
        }
    }

    protected fun loadView(): Boolean {
        if (userVisibleHint) {
            lazyLoad()
            isLoaded = true
            return true
        }
        return false
    }

    override fun onStop() {
        super.onStop()
        isLoaded = false
    }
}