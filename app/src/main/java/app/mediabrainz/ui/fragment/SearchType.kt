package app.mediabrainz.ui.fragment

import app.mediabrainz.ui.R


enum class SearchType (val res: Int) {

    ANNOTATION(R.string.searchtype_annotation),
    AREA(R.string.searchtype_area),
    BARCODE(R.string.searchtype_barcode),
    CDSTUB(R.string.searchtype_cdstub),
    EVENT(R.string.searchtype_event),
    INSTRUMENT(R.string.searchtype_instrument),
    LABEL(R.string.searchtype_label),
    PLACE(R.string.searchtype_place),
    RELEASE(R.string.searchtype_release),
    SERIES(R.string.searchtype_series),
    TAG(R.string.searchtype_tag),
    URL(R.string.searchtype_url),
    WORK(R.string.searchtype_work),
    USER(R.string.searchtype_user)

}
