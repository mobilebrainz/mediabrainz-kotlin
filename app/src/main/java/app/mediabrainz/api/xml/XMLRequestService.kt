package app.mediabrainz.api.xml

import app.mediabrainz.api.core.Config.WEB_SERVICE_PREFIX
import app.mediabrainz.api.xml.entity.MetadataXML
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.*


interface XMLRequestService {

    @POST(WEB_SERVICE_PREFIX + "{path}")
    fun postMetadata(
        @Path("path") path: String,
        @Body metadata: MetadataXML,
        //@QueryMap params: Map<String, String>
        @Query("client") client: String,
        @Query("access_token") accessToken: String
    ): Deferred<Response<MetadataXML>>


}