package app.mediabrainz.ui.extension

import androidx.annotation.StringRes
import app.mediabrainz.ui.App


fun getString(@StringRes id: Int) = App.instance.resources.getString(id)