package app.mediabrainz.api.retrofit

import app.mediabrainz.api.core.Config.AREA_QUERY
import app.mediabrainz.api.core.Config.ARTIST_QUERY
import app.mediabrainz.api.core.Config.COLLECTION_QUERY
import app.mediabrainz.api.core.Config.EVENT_QUERY
import app.mediabrainz.api.core.Config.LABEL_QUERY
import app.mediabrainz.api.core.Config.PLACE_QUERY
import app.mediabrainz.api.core.Config.RECORDING_QUERY
import app.mediabrainz.api.core.Config.RELEASE_GROUP_QUERY
import app.mediabrainz.api.core.Config.RELEASE_QUERY
import app.mediabrainz.api.core.Config.SERIES_QUERY
import app.mediabrainz.api.core.Config.URL_QUERY
import app.mediabrainz.api.core.Config.WORK_QUERY
import app.mediabrainz.api.response.*
import app.mediabrainz.domain.model.*
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap


interface BrowseRequestService {

    @GET(AREA_QUERY)
    fun browseArea(@QueryMap params: Map<String, String>): Deferred<Response<AreaBrowseResponse>>

    @GET(ARTIST_QUERY)
    fun browseArtist(@QueryMap params: Map<String, String>): Deferred<Response<ArtistBrowseResponse>>

    @GET(COLLECTION_QUERY)
    fun browseCollection(@QueryMap params: Map<String, String>): Deferred<Response<CollectionBrowseResponse>>

    @GET(EVENT_QUERY)
    fun browseEvent(@QueryMap params: Map<String, String>): Deferred<Response<EventBrowseResponse>>

    @GET(LABEL_QUERY)
    fun browseLabel(@QueryMap params: Map<String, String>): Deferred<Response<LabelBrowseResponse>>

    @GET(PLACE_QUERY)
    fun browsePlace(@QueryMap params: Map<String, String>): Deferred<Response<PlaceBrowseResponse>>

    @GET(RECORDING_QUERY)
    fun browseRecording(@QueryMap params: Map<String, String>): Deferred<Response<RecordingBrowseResponse>>

    @GET(RELEASE_QUERY)
    fun browseRelease(@QueryMap params: Map<String, String>): Deferred<Response<ReleaseBrowseResponse>>

    @GET(RELEASE_GROUP_QUERY)
    fun browseReleaseGroup(@QueryMap params: Map<String, String>): Deferred<Response<ReleaseGroupBrowseResponse>>

    @GET(SERIES_QUERY)
    fun browseSeries(@QueryMap params: Map<String, String>): Deferred<Response<SeriesBrowseResponse>>

    //@GET(URL_QUERY)
    //fun browseUrl(@QueryMap params: Map<String, String>): Deferred<Response<UrlResponse>>

    @GET(WORK_QUERY)
    fun browseWork(@QueryMap params: Map<String, String>): Deferred<Response<WorkBrowseResponse>>

}