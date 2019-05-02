package app.mediabrainz.domain.repository

import androidx.annotation.StringRes


class Resource<T> private constructor(
    var status: Status,
    val data: T?,
    @StringRes val messageResId: Int = -1
) {

    enum class Status {
        SUCCESS, ERROR, LOADING, INVALID
    }

    companion object {
        fun <T> success(data: T) = Resource(Status.SUCCESS, data)

        fun <T> error(@StringRes messageResId: Int, data: T? = null) =
            Resource(Status.ERROR, data, messageResId)

        fun <T> loading(data: T? = null) = Resource(Status.LOADING, data)
    }
}
