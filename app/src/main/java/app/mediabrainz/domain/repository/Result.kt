package app.mediabrainz.domain.repository


sealed class Result {

    data class Success<T>(val data: T) : Result()
    data class Error(val msg: String, val throwable: Throwable?) : Result()
    class Loading : Result()

    companion object {
        fun <T> success(data: T) = Success(data)
        fun error(msg: String, throwable: Throwable? = null) = Error(msg, throwable)
        fun loading() = Loading()
    }

}