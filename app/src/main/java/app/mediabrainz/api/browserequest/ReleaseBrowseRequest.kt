package app.mediabrainz.api.browserequest

import app.mediabrainz.api.core.Config
import app.mediabrainz.api.core.WebService
import app.mediabrainz.api.core.getStringFromArray
import app.mediabrainz.api.lookupbrowse.BaseBrowseRequest
import app.mediabrainz.api.lookupbrowse.BrowseEntityTypeInterface
import app.mediabrainz.api.lookupbrowse.BrowseIncTypeInterface
import app.mediabrainz.api.lookupbrowse.BrowseParamType
import app.mediabrainz.api.lookupbrowse.EntityType.AREA_ENTITY
import app.mediabrainz.api.lookupbrowse.EntityType.ARTIST_ENTITY
import app.mediabrainz.api.lookupbrowse.EntityType.COLLECTION_ENTITY
import app.mediabrainz.api.lookupbrowse.EntityType.LABEL_ENTITY
import app.mediabrainz.api.lookupbrowse.EntityType.RECORDING_ENTITY
import app.mediabrainz.api.lookupbrowse.EntityType.RELEASE_GROUP_ENTITY
import app.mediabrainz.api.lookupbrowse.EntityType.TRACK_ARTIST_ENTITY
import app.mediabrainz.api.lookupbrowse.EntityType.TRACK_ENTITY
import app.mediabrainz.api.lookupbrowse.IncType.ALIASES_INC
import app.mediabrainz.api.lookupbrowse.IncType.ANNOTATION_INC
import app.mediabrainz.api.lookupbrowse.IncType.ARTIST_CREDITS_INC
import app.mediabrainz.api.lookupbrowse.IncType.DISCIDS_INC
import app.mediabrainz.api.lookupbrowse.IncType.LABELS_INC
import app.mediabrainz.api.lookupbrowse.IncType.MEDIA_INC
import app.mediabrainz.api.lookupbrowse.IncType.RECORDINGS_INC
import app.mediabrainz.api.lookupbrowse.IncType.RELEASE_GROUPS_INC
import app.mediabrainz.api.response.ReleaseBrowseResponse
import app.mediabrainz.api.response.ReleaseGroupType
import app.mediabrainz.api.response.ReleaseStatus
import app.mediabrainz.api.retrofit.BrowseRequestService


class ReleaseBrowseRequest(entityType: ReleaseBrowseEntityType, mbid: String) :
    BaseBrowseRequest<ReleaseBrowseResponse, ReleaseBrowseIncType, ReleaseBrowseEntityType>(entityType, mbid) {

    override fun browse() = WebService
        .createJsonRetrofitService(BrowseRequestService::class.java, Config.WEB_SERVICE)
        .browseRelease(buildParams())

    fun addReleaseGroupTypes(vararg types: ReleaseGroupType): ReleaseBrowseRequest {
        addParam(BrowseParamType.TYPE, getStringFromArray(types, "|").toLowerCase())
        return this
    }

    fun addStatus(status: ReleaseStatus): ReleaseBrowseRequest {
        addParam(BrowseParamType.STATUS, status.status)
        return this
    }
}

enum class ReleaseBrowseEntityType(val type: String) : BrowseEntityTypeInterface {
    AREA(AREA_ENTITY),
    ARTIST(ARTIST_ENTITY),
    COLLECTION(COLLECTION_ENTITY),
    LABEL(LABEL_ENTITY),
    RECORDING(RECORDING_ENTITY),
    RELEASE_GROUP(RELEASE_GROUP_ENTITY),
    TRACK(TRACK_ENTITY),
    TRACK_ARTIST(TRACK_ARTIST_ENTITY);

    override fun toString() = type
}

enum class ReleaseBrowseIncType(val inc: String) : BrowseIncTypeInterface {
    ALIASES(ALIASES_INC),
    ANNOTATION(ANNOTATION_INC),
    ARTIST_CREDITS(ARTIST_CREDITS_INC),
    LABELS(LABELS_INC),
    RECORDINGS(RECORDINGS_INC),
    RELEASE_GROUPS(RELEASE_GROUPS_INC),
    DISCIDS(DISCIDS_INC),
    MEDIA(MEDIA_INC);

    override fun toString() = inc
}