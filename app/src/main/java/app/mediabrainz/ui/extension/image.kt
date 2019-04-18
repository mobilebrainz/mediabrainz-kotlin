package app.mediabrainz.ui.extension

import android.widget.ImageView
import app.mediabrainz.api.response.CoverArtSize
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy


fun ImageView.show(
    imageUrl: String,
    callback: RequestListenerCallback,
    width: Int = CoverArtSize.SMALL_SIZE.size,
    height: Int = CoverArtSize.SMALL_SIZE.size
) {
    Glide.with(this.context)
        .load(imageUrl)
        .override(width, height)
        .fitCenter()
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .listener(callback)
        .into(this)
}

fun ImageView.show(
    imageUrl: String,
    progressAction: () -> Unit,
    width: Int = CoverArtSize.SMALL_SIZE.size,
    height: Int = CoverArtSize.SMALL_SIZE.size
) {
    this.show(imageUrl, RequestListenerCallback(progressAction), width, height)
}