package app.mediabrainz.api.lookupbrowse

import kotlinx.coroutines.Deferred
import retrofit2.Response


interface BrowseRequestInterface<R, P>
        where R : BrowseResponseInterface, P : Enum<P>, P : BrowseIncTypeInterface {

    fun browse(): Deferred<Response<R>>
    fun browse(limit: Int, offset: Int): Deferred<Response<R>>

    fun addAccessToken(accessToken: String): BrowseRequestInterface<R, P>
    fun addIncs(vararg incTypes: P): BrowseRequestInterface<R, P>
    fun addRels(vararg relTypes: RelsType): BrowseRequestInterface<R, P>

}

interface BrowseIncTypeInterface
interface BrowseResponseInterface
interface BrowseEntityTypeInterface

enum class BrowseEmptyIncType : BrowseIncTypeInterface

enum class BrowseParamType(val param: String) {
    ACCESS_TOKEN("access_token"),
    FORMAT("fmt"),
    LIMIT("limit"),
    OFFSET("offset"),

    //TODO: remove?
    INC("inc"),

    /**
     * only for inc=releases or inc=release-groups
     * https://musicbrainz.org/ws/2/artist/79491354-3d83-40e3-9d8e-7592d58d790a?fmt=json&inc=release-groups&type=album
     */
    TYPE("type"),

    /**
     * only for inc=releases
     */
    STATUS("status");

    override fun toString() = param
}