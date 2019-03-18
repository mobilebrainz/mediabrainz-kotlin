package app.mediabrainz.domain.datasource

import androidx.annotation.StringRes


class NetworkState(val status: Status, @StringRes val messageResId: Int = -1) {

    enum class Status {
        SUCCESS, ERROR, LOADING
    }

    companion object {
        fun success() = NetworkState(Status.SUCCESS)
        fun error(@StringRes messageResId: Int) = NetworkState(Status.ERROR, messageResId)
        fun loading() = NetworkState(Status.LOADING)
    }

}