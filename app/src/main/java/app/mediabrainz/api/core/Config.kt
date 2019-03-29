package app.mediabrainz.api.core


object Config {
    const val WEB_SERVICE = "https://musicbrainz.org"
    const val WEB_SERVICE_PREFIX = "/ws/2/"
    const val FORMAT_JSON = "json"

    const val ANNOTATION_QUERY = WEB_SERVICE_PREFIX + "annotation"
    const val AREA_QUERY = WEB_SERVICE_PREFIX + "area"
    const val ARTIST_QUERY = WEB_SERVICE_PREFIX + "artist"
    const val CDSTUB_QUERY = WEB_SERVICE_PREFIX + "cdstub"
    const val COLLECTION_QUERY = WEB_SERVICE_PREFIX + "collection"
    const val INSTRUMENT_QUERY = WEB_SERVICE_PREFIX + "instrument"
    const val LABEL_QUERY = WEB_SERVICE_PREFIX + "label"
    const val PLACE_QUERY = WEB_SERVICE_PREFIX + "place"
    const val RATING_QUERY = WEB_SERVICE_PREFIX + "rating"
    const val RECORDING_QUERY = WEB_SERVICE_PREFIX + "recording"
    const val RELEASE_QUERY = WEB_SERVICE_PREFIX + "release"
    const val RELEASE_GROUP_QUERY = WEB_SERVICE_PREFIX + "release-group"
    const val TAG_QUERY = WEB_SERVICE_PREFIX + "tag"
    const val WORK_QUERY = WEB_SERVICE_PREFIX + "work"
    const val EVENT_QUERY = WEB_SERVICE_PREFIX + "event"
    const val ISRC_QUERY = WEB_SERVICE_PREFIX + "isrc"
    const val ISWC_QUERY = WEB_SERVICE_PREFIX + "iswc"
    const val SERIES_QUERY = WEB_SERVICE_PREFIX + "series"
    const val URL_QUERY = WEB_SERVICE_PREFIX + "url"
    const val DISCID_QUERY = WEB_SERVICE_PREFIX + "discid"

    const val OAUTH_WEB_SERVICE = "https://musicbrainz.org"
    const val OAUTH_TEST_WEB_SERVICE = "https://test.musicbrainz.org"
    const val OAUTH_AUTHORIZE_QUERY  = "/oauth2/authorize"
    const val OAUTH_TOKEN_QUERY = "/oauth2/token"
    const val OAUTH_USER_INFO_QUERY = "/oauth2/userinfo"
    const val OAUTH_TOKEN_INFO_QUERY = "/oauth2/tokeninfo"

}