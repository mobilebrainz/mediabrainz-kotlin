package app.mediabrainz.api.lookupbrowse

enum class RelsType(val param: String) :
    LookupIncTypeInterface,
    BrowseIncTypeInterface {

    AREA_RELS("area-rels"),
    ARTIST_RELS("artist-rels"),
    EVENT_RELS("event-rels"),
    INSTRUMENT_RELS("instrument-rels"),
    LABEL_RELS("label-rels"),
    PLACE_RELS("place-rels"),
    RECORDING_RELS("recording-rels"),
    RELEASE_RELS("release-rels"),
    RELEASE_GROUP_RELS("release-group-rels"),
    SERIES_RELS("series-rels"),
    URL_RELS("url-rels"),
    WORK_RELS("work-rels");

    override fun toString() = param
}