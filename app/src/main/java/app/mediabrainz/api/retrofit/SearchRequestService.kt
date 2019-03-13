package app.mediabrainz.api.retrofit

import app.mediabrainz.api.core.Config.ANNOTATION_QUERY
import app.mediabrainz.api.core.Config.AREA_QUERY
import app.mediabrainz.api.core.Config.ARTIST_QUERY
import app.mediabrainz.api.core.Config.CDSTUB_QUERY
import app.mediabrainz.api.core.Config.EVENT_QUERY
import app.mediabrainz.api.core.Config.INSTRUMENT_QUERY
import app.mediabrainz.api.core.Config.LABEL_QUERY
import app.mediabrainz.api.core.Config.PLACE_QUERY
import app.mediabrainz.api.core.Config.RECORDING_QUERY
import app.mediabrainz.api.core.Config.RELEASE_GROUP_QUERY
import app.mediabrainz.api.core.Config.RELEASE_QUERY
import app.mediabrainz.api.core.Config.SERIES_QUERY
import app.mediabrainz.api.core.Config.TAG_QUERY
import app.mediabrainz.api.core.Config.WORK_QUERY
import app.mediabrainz.api.response.*
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap


interface SearchRequestService {

    @GET(ANNOTATION_QUERY)
    fun searchAnnotation(@QueryMap params: Map<String, String>): Deferred<Response<AnnotationSearchResponse>>

    @GET(AREA_QUERY)
    fun searchArea(@QueryMap params: Map<String, String>): Deferred<Response<AreaSearchResponse>>

    @GET(ARTIST_QUERY)
    fun searchArtist(@QueryMap params: Map<String, String>): Deferred<Response<ArtistSearchResponse>>

    @GET(CDSTUB_QUERY)
    fun searchCDStub(@QueryMap params: Map<String, String>): Deferred<Response<CDStubResponse>>

    @GET(EVENT_QUERY)
    fun searchEvent(@QueryMap params: Map<String, String>): Deferred<Response<EventSearchResponse>>

    @GET(INSTRUMENT_QUERY)
    fun searchInstrument(@QueryMap params: Map<String, String>): Deferred<Response<InstrumentSearchResponse>>

    @GET(LABEL_QUERY)
    fun searchLabel(@QueryMap params: Map<String, String>): Deferred<Response<LabelSearchResponse>>

    @GET(PLACE_QUERY)
    fun searchPlace(@QueryMap params: Map<String, String>): Deferred<Response<PlaceSearchResponse>>

    @GET(RECORDING_QUERY)
    fun searchRecording(@QueryMap params: Map<String, String>): Deferred<Response<ArtistSearchResponse>>

    @GET(RELEASE_QUERY)
    fun searchRelease(@QueryMap params: Map<String, String>): Deferred<Response<ReleaseSearchResponse>>

    @GET(RELEASE_GROUP_QUERY)
    fun searchReleaseGroup(@QueryMap params: Map<String, String>): Deferred<Response<ReleaseGroupSearchResponse>>

    @GET(SERIES_QUERY)
    fun searchSeries(@QueryMap params: Map<String, String>): Deferred<Response<SeriesSearchResponse>>

    @GET(TAG_QUERY)
    fun searchTag(@QueryMap params: Map<String, String>): Deferred<Response<TagSearchResponse>>

    @GET(WORK_QUERY)
    fun searchWork(@QueryMap params: Map<String, String>): Deferred<Response<WorkSearchResponse>>

}