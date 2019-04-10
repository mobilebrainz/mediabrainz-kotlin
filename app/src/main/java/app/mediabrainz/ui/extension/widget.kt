package app.mediabrainz.ui.extension

import android.widget.TextView


fun TextView.trim(): String {
    text = text.trim { it <= ' ' }
    return text.toString()
}
