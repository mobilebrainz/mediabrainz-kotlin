package app.mediabrainz.api.search

import kotlinx.coroutines.Deferred
import retrofit2.Response


interface SearchRequestInterface <R, P>
    where R : SearchResponseInterface, P : Enum<P>, P : SearchFieldInterface {

    fun search(): Deferred<Response<R>>
    fun search(query: String): Deferred<Response<R>>
    fun search(limit: Int, offset: Int): Deferred<Response<R>>
    fun search(query: String, limit: Int, offset: Int): Deferred<Response<R>>

    /**
    val deferred = ApiRequestProvider.createRecordingSearchRequest()
        .add(ARTIST, artist)
        .add(AND).add(RELEASE, release)
        .add(AND).add(NOT).add(RECORDING, recording)
        .search(limit, offset)

    without AND (default):
    val deferred = ApiRequestProvider.createRecordingSearchRequest()
        .add(ARTIST, artist).add(RELEASE, release).add(NOT).add(RECORDING, recording)
        .search(limit, offset)

     */
    fun add(query: String): SearchRequestInterface<R, P>
    fun add(searchField: P, value: String): SearchRequestInterface<R, P>
    fun add(operator: LuceneOperator): SearchRequestInterface<R, P>

    /**
    val deferred = (ApiRequestProvider.createRecordingSearchRequest() +
            (ARTIST to artist) +
            AND + (RELEASE to release) +
            AND + NOT + (RECORDING to recording))
        .search(limit, offset)

    without AND (default):
    val deferred = (ApiRequestProvider.createRecordingSearchRequest() +
                (ARTIST to artist) + (RELEASE to release) + NOT + (RECORDING to recording))
            .search(limit, offset)

     */
    operator fun plus(query: String) = add(query)
    operator fun plus(operator: LuceneOperator) = add(operator)
    operator fun plus(pair: Pair<P, String>) = add(pair.first, pair.second)
    
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
