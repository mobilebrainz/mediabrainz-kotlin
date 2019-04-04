package app.mediabrainz.api.searchrequest

import app.mediabrainz.api.core.Config
import app.mediabrainz.api.core.WebService
import app.mediabrainz.api.response.WorkSearchResponse
import app.mediabrainz.api.retrofit.SearchRequestService
import app.mediabrainz.api.search.BaseSearchRequest
import app.mediabrainz.api.search.SearchFieldInterface


class WorkSearchRequest :
    BaseSearchRequest<WorkSearchResponse, WorkSearchField>() {

    override fun search() = createJsonRetrofitService().searchWork(buildParams())
}

enum class WorkSearchField(val field: String) : SearchFieldInterface {
    /**
     * the aliases/misspellings for this work
     */
    ALIAS("alias"),

    /**
     * artist id
     */
    ARID("arid"),

    /**
     * artist name, an artist in the context of a work is an artist-work relation such as composer or lyricist
     */
    ARTIST("artist"),

    /**
     * disambiguation comment
     */
    COMMENT("comment"),

    /**
     * ISWC of work
     */
    ISWC("iswc"),

    /**
     * Lyrics language of work
     */
    LANG("lang"),

    /**
     * folksonomy tag
     */
    TAG("tag"),

    /**
     * work type
     */
    TYPE("type"),

    /**
     * work id
     */
    WID("wid"),

    /**
     * name of work
     */
    WORK("work"),

    /**
     * name of the work with any accent characters retained
     */
    WORK_ACCENT("workaccent");

    override fun toString() = field
}