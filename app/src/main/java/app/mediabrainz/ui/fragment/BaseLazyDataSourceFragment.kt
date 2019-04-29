package app.mediabrainz.ui.fragment


abstract class BaseLazyDataSourceFragment : BaseDataSourceFragment() {

    var isLoaded: Boolean = false

    protected abstract fun lazyLoad()

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser && isResumed && !isLoaded) {
            lazyLoad()
            isLoaded = true
        }
        showError(isVisibleToUser)
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
