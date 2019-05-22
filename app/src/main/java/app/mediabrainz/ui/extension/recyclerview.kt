package app.mediabrainz.ui.extension

import android.view.View
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.recyclerview.widget.RecyclerView


fun <T : View> RecyclerView.ViewHolder.findViewById(@IdRes id: Int): T =
    itemView.findViewById(id)

fun RecyclerView.ViewHolder.getStringFromRes(@StringRes id: Int) =
    itemView.resources.getString(id)
