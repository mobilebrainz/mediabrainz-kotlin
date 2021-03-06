package app.mediabrainz.api.lookupbrowse

import app.mediabrainz.api.core.Config
import app.mediabrainz.api.core.Config.FORMAT_JSON
import app.mediabrainz.api.core.WebService
import app.mediabrainz.api.core.getStringFromList
import app.mediabrainz.api.lookupbrowse.BrowseParamType.FORMAT
import app.mediabrainz.api.lookupbrowse.BrowseParamType.INC
import app.mediabrainz.api.retrofit.BrowseRequestService
import kotlinx.coroutines.Deferred
import retrofit2.Response


abstract class BaseBrowseRequest<R, P1, P2>(val entityType: P2, val mbid: String) :
    BrowseRequestInterface<R, P1>
        where R : BrowseResponseInterface,
              P1 : Enum<P1>, P1 : BrowseIncTypeInterface,
              P2 : Enum<P2>, P2 : BrowseEntityTypeInterface {

    private val incs: MutableList<BrowseIncTypeInterface> = mutableListOf()
    private val params: MutableMap<BrowseParamType, String> = mutableMapOf(
        FORMAT to FORMAT_JSON
    )

    override fun addAccessToken(accessToken: String): BrowseRequestInterface<R, P1> {
        if (accessToken.isNotBlank()) {
            params[BrowseParamType.ACCESS_TOKEN] = accessToken
        }
        return this
    }

    fun addParam(param: BrowseParamType, value: String): BrowseRequestInterface<R, P1> {
        params[param] = value
        return this
    }

    override fun addIncs(vararg incTypes: P1): BrowseRequestInterface<R, P1> {
        incs.addAll(incTypes.asList())
        return this
    }

    override fun addRels(vararg relTypes: RelsType): BrowseRequestInterface<R, P1> {
        incs.addAll(relTypes.asList())
        return this
    }

    override fun browse(limit: Int, offset: Int): Deferred<Response<R>> {
        params[BrowseParamType.LIMIT] = Integer.toString(limit)
        params[BrowseParamType.OFFSET] = Integer.toString(offset)
        return browse()
    }

    protected fun buildParams(): Map<String, String> {
        val map = mutableMapOf(entityType.toString() to mbid)
        for ((key, value) in params) {
            map[key.toString()] = value
        }
        /*
        // set digeat auth
        if (!params.containsKey(BrowseParamType.ACCESS_TOKEN)) {
            for (inc in incs) {
                for (authIncs in AUTHORIZATED_INCS) {
                    if (inc.toString() == authIncs) {
                        digestAuth = true
                    }
                }
            }
        }
        */
        val incStr = getStringFromList(incs, "+")
        if (incStr.isNotBlank()) {
            map[INC.param] = incStr
        }
        incs.clear()
        return map
    }

    protected fun createJsonRetrofitService() = WebService
        .createJsonRetrofitService(BrowseRequestService::class.java, Config.WEB_SERVICE)


}