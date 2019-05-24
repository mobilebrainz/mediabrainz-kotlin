package app.mediabrainz.api.lookupbrowse

import app.mediabrainz.api.response.RelationResponse
import com.squareup.moshi.Json


abstract class BaseLookupEntity {

    /**
     * inc=...-rels
     */
    @field:Json(name = "relations")
    open var relations: List<RelationResponse>? = null
}