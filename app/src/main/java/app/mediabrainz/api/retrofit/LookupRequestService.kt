package app.mediabrainz.api.retrofit

import app.mediabrainz.api.core.Config.AREA_QUERY
import app.mediabrainz.api.core.Config.ARTIST_QUERY
import app.mediabrainz.api.core.Config.COLLECTION_QUERY
import app.mediabrainz.api.core.Config.DISCID_QUERY
import app.mediabrainz.api.core.Config.EVENT_QUERY
import app.mediabrainz.api.core.Config.INSTRUMENT_QUERY
import app.mediabrainz.api.core.Config.ISRC_QUERY
import app.mediabrainz.api.core.Config.ISWC_QUERY
import app.mediabrainz.api.core.Config.LABEL_QUERY
import app.mediabrainz.api.core.Config.PLACE_QUERY
import app.mediabrainz.api.core.Config.RECORDING_QUERY
import app.mediabrainz.api.core.Config.RELEASE_GROUP_QUERY
import app.mediabrainz.api.core.Config.RELEASE_QUERY
import app.mediabrainz.api.core.Config.SERIES_QUERY
import app.mediabrainz.api.core.Config.URL_QUERY
import app.mediabrainz.api.core.Config.WORK_QUERY
import app.mediabrainz.api.response.*
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap


interface LookupRequestService {

    @GET(AREA_QUERY + "/{mbid}")
    fun lookupArea(@Path("mbid") mbid: String, @QueryMap params: Map<String, String>):
            Deferred<Response<AreaResponse>>

    @GET(ARTIST_QUERY + "/{mbid}")
    fun lookupArtist(@Path("mbid") mbid: String, @QueryMap params: Map<String, String>):
            Deferred<Response<ArtistResponse>>

    @GET(COLLECTION_QUERY + "/{mbid}")
    fun lookupCollection(@Path("mbid") mbid: String, @QueryMap params: Map<String, String>):
            Deferred<Response<CollectionResponse>>

    @GET(DISCID_QUERY + "/{mbid}")
    fun lookupDisc(@Path("mbid") mbid: String, @QueryMap params: Map<String, String>):
            Deferred<Response<DiscResponse>>

    @GET(EVENT_QUERY + "/{mbid}")
    fun lookupEvent(@Path("mbid") mbid: String, @QueryMap params: Map<String, String>):
            Deferred<Response<EventResponse>>

    @GET(LABEL_QUERY + "/{mbid}")
    fun lookupLabel(@Path("mbid") mbid: String, @QueryMap params: Map<String, String>):
            Deferred<Response<LabelResponse>>

    @GET(INSTRUMENT_QUERY + "/{mbid}")
    fun lookupInstrument(@Path("mbid") mbid: String, @QueryMap params: Map<String, String>):
            Deferred<Response<InstrumentResponse>>

    @GET(ISRC_QUERY + "/{mbid}")
    fun lookupISRC(@Path("mbid") mbid: String, @QueryMap params: Map<String, String>):
            Deferred<Response<ISRCResponse>>

    @GET(ISWC_QUERY + "/{mbid}")
    fun lookupISWC(@Path("mbid") mbid: String, @QueryMap params: Map<String, String>):
            Deferred<Response<ISWCResponse>>

    @GET(PLACE_QUERY + "/{mbid}")
    fun lookupPlace(@Path("mbid") mbid: String, @QueryMap params: Map<String, String>):
            Deferred<Response<PlaceResponse>>

    @GET(RECORDING_QUERY + "/{mbid}")
    fun lookupRecording(@Path("mbid") mbid: String, @QueryMap params: Map<String, String>):
            Deferred<Response<RecordingResponse>>

    @GET(RELEASE_QUERY + "/{mbid}")
    fun lookupRelease(@Path("mbid") mbid: String, @QueryMap params: Map<String, String>):
            Deferred<Response<ReleaseResponse>>

    @GET(RELEASE_GROUP_QUERY + "/{mbid}")
    fun lookupReleaseGroup(@Path("mbid") mbid: String, @QueryMap params: Map<String, String>):
            Deferred<Response<ReleaseGroupResponse>>

    @GET(SERIES_QUERY + "/{mbid}")
    fun lookupSeries(@Path("mbid") mbid: String, @QueryMap params: Map<String, String>):
            Deferred<Response<SeriesResponse>>

    @GET(URL_QUERY + "/{mbid}")
    fun lookupUrl(@Path("mbid") mbid: String, @QueryMap params: Map<String, String>):
            Deferred<Response<UrlResponse>>

    @GET(WORK_QUERY + "/{mbid}")
    fun lookupWork(@Path("mbid") mbid: String, @QueryMap params: Map<String, String>):
            Deferred<Response<WorkResponse>>
}