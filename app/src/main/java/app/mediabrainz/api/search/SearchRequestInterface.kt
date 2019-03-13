package app.mediabrainz.api.search

import kotlinx.coroutines.Deferred
import retrofit2.Response


interface SearchRequestInterface <R, P>
    where R : SearchResponseInterface, P : Enum<P>, P : SearchFieldInterface {

    fun search(): Deferred<Response<R>>
    fun search(query: String): Deferred<Response<R>>
    fun search(limit: Int, offset: Int): Deferred<Response<R>>
    fun search(query: String, limit: Int, offset: Int): Deferred<Response<R>>

    fun add(query: String): SearchRequestInterface<R, P>
    fun add(searchField: P, value: String): SearchRequestInterface<R, P>
    fun add(operator: LuceneOperator): SearchRequestInterface<R, P>
}

interface SearchFieldInterface
interface SearchResponseInterface

enum class SearchParamType(val param: String) {
    FORMAT("fmt"),
    QUERY("query"),
    LIMIT("limit"),
    OFFSET("offset");

    override fun toString() = param
}
