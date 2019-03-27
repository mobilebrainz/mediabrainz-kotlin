package app.mediabrainz.api.response

import app.mediabrainz.api.lookupbrowse.BrowseResponseInterface
import app.mediabrainz.api.lookupbrowse.LookupResponseInterface
import app.mediabrainz.api.search.SearchResponseInterface
import com.squareup.moshi.Json


data class UrlResponse(
    @field:Json(name = "id") val mbid: String,
    @field:Json(name = "resource") val resource: String,
    //todo: check
    @field:Json(name = "type") val type: String?,
    /**
     * from search request
     */
    @field:Json(name = "relation-list") val relationList: List<RelationList>?,
    /**
     * inc=...-rels
     */
    @field:Json(name = "relations") val relations: List<RelationResponse>?
) : LookupResponseInterface, BrowseResponseInterface

class UrlSearchResponse : BaseItemsResponse<UrlResponse>(), SearchResponseInterface {
    @field:Json(name = "urls")
    override var items: List<UrlResponse> = ArrayList()
}