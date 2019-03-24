package app.mediabrainz.api.lookupbrowse

import kotlinx.coroutines.Deferred
import retrofit2.Response


interface LookupRequestInterface<R, P>
        where R : LookupResponseInterface, P : Enum<P>, P : LookupIncTypeInterface {

    fun addIncs(vararg incTypes: P): LookupRequestInterface<R, P>
    fun addRels(vararg relTypes: RelsType): LookupRequestInterface<R, P>
    fun lookup(): Deferred<Response<R>>
}

interface LookupIncTypeInterface
interface LookupResponseInterface

enum class LookupEmptyIncType : LookupIncTypeInterface

enum class LookupParamType(val param: String) {
    ACCESS_TOKEN("access_token"),
    FORMAT("fmt"),
    INC("inc"),

    /**
     * primary or secondary type for release-group
     * only for inc=releases or inc=release-groups
     * https://musicbrainz.org/ws/2/artist/79491354-3d83-40e3-9d8e-7592d58d790a?fmt=json&inc=release-groups&type=album
     */
    TYPE("type"),

    /**
     * only for inc=releases
     */
    STATUS("status"),

    /**
     * only for discid : /ws/2/discid/I5l9cCSFccLKFEKS.7wqSZAorPU-?toc=1+12+267257+150+22767+41887+58317+72102+91375+104652+115380+132165+143932+159870+174597
     */
    TOC("toc"),

    /**
     * only for discid : /ws/2/discid/I5l9cCSFccLKFEKS.7wqSZAorPU-?media-format=all
     */
    MEDIA_FORMAT("media-format");

    override fun toString() = param
}