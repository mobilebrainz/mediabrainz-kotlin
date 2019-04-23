package app.mediabrainz.ui.extension

import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.TextView


fun TextView.trim(): String {
    text = text.trim { it <= ' ' }
    return text.toString()
}

fun TextView.setEmptyVisibility() {
    visibility = if (text.isNotEmpty()) VISIBLE else GONE
}

fun TextView.setEmptyText(t: CharSequence) {
    text = t
    setEmptyVisibility()
}
