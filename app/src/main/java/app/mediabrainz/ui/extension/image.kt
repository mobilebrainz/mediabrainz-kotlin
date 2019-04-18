package app.mediabrainz.ui.extension

import android.graphics.drawable.Drawable
import android.widget.ImageView
import app.mediabrainz.api.response.CoverArtSize
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target


fun ImageView.show(
    imageUrl: String,
    onResourceReady: () -> Unit,
    onLoadFailed: () -> Unit,
    width: Int = CoverArtSize.SMALL_SIZE.size,
    height: Int = CoverArtSize.SMALL_SIZE.size
) {
    val listener = object : RequestListener<Drawable> {
        override fun onResourceReady(
            resource: Drawable?,
            model: Any?,
            target: Target<Drawable>?,
            dataSource: DataSource?,
            isFirstResource: Boolean
        ): Boolean {
            onResourceReady.invoke()
            return false
        }

        override fun onLoadFailed(
            e: GlideException?,
            model: Any?,
            target: Target<Drawable>?,
            isFirstResource: Boolean
        ): Boolean {
            onLoadFailed.invoke()
            return false
        }
    }

    Glide.with(this.context)
        .load(imageUrl)
        .override(width, height)
        .fitCenter()
        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
        .listener(listener)
        .into(this)
}