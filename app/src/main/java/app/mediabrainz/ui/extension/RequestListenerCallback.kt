package app.mediabrainz.ui.extension

import android.graphics.drawable.Drawable
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target


class RequestListenerCallback(val progressAction: () -> Unit) : RequestListener<Drawable> {

    private var errorAction: (() -> Unit)? = null

    constructor(progressAction: () -> Unit, errorAction: () -> Unit) : this(progressAction) {
        this.errorAction = errorAction
    }

    override fun onLoadFailed(
        e: GlideException?, model: Any, target: Target<Drawable>, isFirstResource: Boolean
    ): Boolean {
        progressAction.invoke()
        return false
    }

    override fun onResourceReady(
        resource: Drawable, model: Any, target: Target<Drawable>, dataSource: DataSource, isFirstResource: Boolean
    ): Boolean {
        progressAction.invoke()
        errorAction?.invoke()
        return false
    }
}
