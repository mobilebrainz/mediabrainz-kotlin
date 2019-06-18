package app.mediabrainz.api.xml

import app.mediabrainz.api.xml.entity.MetadataXML
import app.mediabrainz.api.xml.entity.UserTagXML
import kotlinx.coroutines.Deferred
import retrofit2.Response


interface XMLRequestInterface {

    fun postArtistTags(artistMbid: String, vararg tags: UserTagXML): Deferred<Response<MetadataXML>>
}

enum class XMLPathType (val param: String) {

    RATING("rating"),
    TAG("tag"),
    RELEASE("release"),
    RECORDING("recording");

    override fun toString() = param

}