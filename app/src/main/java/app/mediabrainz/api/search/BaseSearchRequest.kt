package app.mediabrainz.api.search

import app.mediabrainz.api.core.Config.FORMAT_JSON
import kotlinx.coroutines.Deferred
import retrofit2.Response


abstract class BaseSearchRequest<R, P> : SearchServiceInterface<R, P>
        where R : SearchResponseInterface, P : Enum<P>, P : SearchFieldInterface {

    private val expression = LuceneBuilder()
    private val params: MutableMap<SearchParamType, String> = mutableMapOf(
        SearchParamType.QUERY to "empty",
        SearchParamType.FORMAT to FORMAT_JSON
    )

    override fun search(query: String): Deferred<Response<R>> {
        params[SearchParamType.QUERY] = query
        return search()
    }

    override fun search(query: String, limit: Int, offset: Int): Deferred<Response<R>> {
        params[SearchParamType.QUERY] = query
        params[SearchParamType.LIMIT] = Integer.toString(limit)
        params[SearchParamType.OFFSET] = Integer.toString(offset)
        return search()
    }

    override fun search(limit: Int, offset: Int): Deferred<Response<R>> {
        return search(expression.build(), limit, offset)
    }

    override fun add(query: String): SearchServiceInterface<R, P> {
        if (query != "") {
            expression.add(query)
        }
        return this
    }

    override fun add(operator: LuceneOperator): SearchServiceInterface<R, P> {
        expression.add(operator)
        return this
    }

    override fun add(searchField: P, value: String): SearchServiceInterface<R, P> {
        if (value != "") {
            expression.add(searchField, value)
        }
        return this
    }

    protected fun buildParams(): Map<String, String> {
        val map = mutableMapOf<String, String>()
        for ((key, value) in params) {
            map[key.toString()] = value
        }
        val query = expression.build()
        if (query != "") {
            map[SearchParamType.QUERY.param] = query
        }
        return map
    }

}