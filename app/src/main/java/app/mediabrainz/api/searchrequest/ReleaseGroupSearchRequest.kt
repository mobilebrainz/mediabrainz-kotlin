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


/**
 * unconditional search: Release group search terms with no fields search the RELEASE_GROUP field only.
 *  ReleaseGroupSearchService().search("black")
 *  ReleaseGroupSearchService().search("black", 1, 10)
 */
class ReleaseGroupSearchRequest :
    BaseSearchRequest<ReleaseGroupSearchResponse, ReleaseGroupSearchField>() {

    override fun search() = WebService
        .createJsonRetrofitService(SearchRequestService::class.java, Config.WEB_SERVICE)
        .searchReleaseGroup(buildParams())

    fun addPrimaryType(type: ReleaseGroupPrimaryType): ReleaseGroupSearchRequest {
        add(ReleaseGroupSearchField.PRIMARY_TYPE, type.type)
        return this
    }

    fun addSecondaryType(type: ReleaseGroupSecondaryType): ReleaseGroupSearchRequest {
        add(ReleaseGroupSearchField.SECONDARY_TYPE, type.type)
        return this
    }

    override fun add(operator: LuceneOperator): ReleaseGroupSearchRequest {
        super.add(operator)
        return this
    }
}

enum class ReleaseGroupSearchField(val field: String) : SearchFieldInterface {
    /**
     * MBID of the release group’s artist
     */
    ARID("arid"),

    /**
     * release group artist as it appears on the cover (Artist Credit)
     */
    ARTIST("artist"),

    /**
     * “real name” of any artist that is included in the release group’s artist credit
     */
    ARTIST_NAME("artistname"),

    /**
     * release group comment to differentiate similar release groups
     */
    COMMENT("comment"),

    /**
     * name of any artist in multi-artist credits, as it appears on the cover.
     */
    CREDIT_NAME("creditname"),

    /**
     * primary type of the release group (album, single, ep, other)
     */
    PRIMARY_TYPE("primarytype"),

    /**
     * MBID of the release group
     */
    RGID("rgid"),

    /**
     * name of the release group
     */
    RELEASE_GROUP("releasegroup"),

    /**
     * name of the releasegroup with any accent characters retained
     */
    RELEASE_GROUP_ACCENT("releasegroupaccent"),

    /**
     * number of releases in this release group
     */
    RELEASES("releases"),

    /**
     * name of a release that appears in the release group
     */
    RELEASE("release"),

    /**
     * MBID of a release that appears in the release group
     */
    REID("reid"),

    /**
     * secondary type of the release group (audiobook, compilation, interview, live, remix soundtrack, spokenword)
     */
    SECONDARY_TYPE("secondarytype"),

    /**
     * status of a release that appears within the release group
     */
    STATUS("status"),

    /**
     * a tag that appears on the release group
     */
    TAG("tag"),

    /**
     * type of the release group, old type mapping for when we did not have separate primary and secondary types
     */
    TYPE("type");

    override fun toString() = field
}