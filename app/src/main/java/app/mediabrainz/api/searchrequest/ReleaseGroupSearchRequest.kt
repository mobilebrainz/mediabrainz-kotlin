package app.mediabrainz.api.searchrequest

import app.mediabrainz.api.core.Config
import app.mediabrainz.api.core.WebService
import app.mediabrainz.api.response.ReleaseGroupPrimaryType
import app.mediabrainz.api.response.ReleaseGroupSearchResponse
import app.mediabrainz.api.response.ReleaseGroupSecondaryType
import app.mediabrainz.api.retrofit.SearchRequestService
import app.mediabrainz.api.search.BaseSearchRequest
import app.mediabrainz.api.search.LuceneOperator
import app.mediabrainz.api.search.SearchFieldInterface


class ReleaseGroupSearchRequest :
    BaseSearchRequest<ReleaseGroupSearchResponse, ReleaseGroupSearchField>() {

    override fun search() = WebService
        .createJsonRetrofitService(SearchRequestService::class.java, Config.WEB_SERVICE)
        .searchReleaseGroup(buildParams())

    fun addPrimaryType(type: ReleaseGroupPrimaryType): ReleaseGroupSearchRequest {
        add(ReleaseGroupSearchField.PRIMARY_TYPE, type.toString())
        return this
    }

    fun addSecondaryType(type: ReleaseGroupSecondaryType): ReleaseGroupSearchRequest {
        add(ReleaseGroupSearchField.SECONDARY_TYPE, type.toString())
        return this
    }

    override fun add(operator: LuceneOperator): ReleaseGroupSearchRequest {
        super.add(operator)
        return this
    }
}

enum class ReleaseGroupSearchField(val field: String) : SearchFieldInterface {
    ARID("arid"),
    ARTIST("artist"),
    ARTIST_NAME("artistname"),
    COMMENT("comment"),
    CREDIT_NAME("creditname"),
    PRIMARY_TYPE("primarytype"),
    RGID("rgid"),
    RELEASE_GROUP("releasegroup"),
    RELEASE_GROUP_ACCENT("releasegroupaccent"),
    RELEASES("releases"),
    RELEASE("release"),
    REID("reid"),
    SECONDARY_TYPE("secondarytype"),
    STATUS("status"),
    TAG("tag"),
    TYPE("type");

    override fun toString() = field
}