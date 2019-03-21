package app.mediabrainz.api.lookup

import app.mediabrainz.api.core.Config.FORMAT_JSON
import app.mediabrainz.api.core.getStringFromList
import app.mediabrainz.api.lookup.LookupParamType.FORMAT
import app.mediabrainz.api.lookup.LookupParamType.INC
import java.util.*


abstract class BaseLookupRequest<R, P>(val mbid: String) : LookupRequestInterface<R, P>
        where R : LookupResponseInterface, P : Enum<P>, P : LookupIncTypeInterface {

    val incs: MutableList<LookupIncTypeInterface> = mutableListOf()
    val params: MutableMap<LookupParamType, String> = mutableMapOf(
        FORMAT to FORMAT_JSON
    )

    /*
    init {
        if (Config.accessToken != null) {
            params[LookupParamType.ACCESS_TOKEN] = Config.accessToken
        }
    }
    */

    protected fun addParam(param: LookupParamType, value: String): LookupRequestInterface<R, P> {
        params[param] = value
        return this
    }

    override fun addIncs(vararg incTypes: P): LookupRequestInterface<R, P> {
        incs.addAll(incTypes.asList())
        /*
        if (Config.accessToken == null) {
            for (incType in incTypes) {
                for (authType in AUTHORIZATED_INCS) {
                    if (incType.toString() == authType) {
                        digestAuth = true
                    }
                }
            }
        }
        */
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
        val inc = getStringFromList(incs, "+")
        incs.clear()
        if (inc != "") {
            map[INC.param] = inc
        }
        return map
    }

}