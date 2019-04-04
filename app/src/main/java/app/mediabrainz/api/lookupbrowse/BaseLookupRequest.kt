package app.mediabrainz.api.lookupbrowse

import app.mediabrainz.api.core.Config.FORMAT_JSON
import app.mediabrainz.api.core.getStringFromList
import app.mediabrainz.api.lookupbrowse.LookupParamType.FORMAT
import app.mediabrainz.api.lookupbrowse.LookupParamType.INC


abstract class BaseLookupRequest<R, P>(val mbid: String) : LookupRequestInterface<R, P>
        where R : LookupResponseInterface, P : Enum<P>, P : LookupIncTypeInterface {

    private val incs: MutableList<LookupIncTypeInterface> = mutableListOf()

    private val params: MutableMap<LookupParamType, String> = mutableMapOf(
        FORMAT to FORMAT_JSON
    )

    override fun addAccessToken(accessToken: String): LookupRequestInterface<R, P> {
        if (accessToken.isNotBlank()) {
            params[LookupParamType.ACCESS_TOKEN] = accessToken
        }
        return this
    }

    fun addParam(param: LookupParamType, value: String): LookupRequestInterface<R, P> {
        params[param] = value
        return this
    }

    override fun addIncs(vararg incTypes: P): LookupRequestInterface<R, P> {
        incs.addAll(incTypes.asList())
        return this
    }

    override fun addRels(vararg relTypes: RelsType): LookupRequestInterface<R, P> {
        incs.addAll(relTypes.asList())
        return this
    }

    protected fun buildParams(): Map<String, String> {
        val map = mutableMapOf<String, String>()
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

}