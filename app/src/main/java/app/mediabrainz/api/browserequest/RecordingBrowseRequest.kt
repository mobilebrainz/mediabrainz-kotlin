package app.mediabrainz.api.browserequest

import app.mediabrainz.api.lookupbrowse.BaseBrowseRequest
import app.mediabrainz.api.lookupbrowse.BrowseEntityTypeInterface
import app.mediabrainz.api.lookupbrowse.BrowseIncTypeInterface
import app.mediabrainz.api.lookupbrowse.EntityType.ARTIST_ENTITY
import app.mediabrainz.api.lookupbrowse.EntityType.COLLECTION_ENTITY
import app.mediabrainz.api.lookupbrowse.EntityType.RELEASE_ENTITY
import app.mediabrainz.api.lookupbrowse.IncType.ALIASES_INC
import app.mediabrainz.api.lookupbrowse.IncType.ANNOTATION_INC
import app.mediabrainz.api.lookupbrowse.IncType.ARTIST_CREDITS_INC
import app.mediabrainz.api.lookupbrowse.IncType.GENRES_INC
import app.mediabrainz.api.lookupbrowse.IncType.RATINGS_INC
import app.mediabrainz.api.lookupbrowse.IncType.TAGS_INC
import app.mediabrainz.api.lookupbrowse.IncType.USER_GENRES_INC
import app.mediabrainz.api.lookupbrowse.IncType.USER_RATINGS_INC
import app.mediabrainz.api.lookupbrowse.IncType.USER_TAGS_INC
import app.mediabrainz.api.response.RecordingBrowseResponse


class RecordingBrowseRequest(entityType: RecordingBrowseEntityType, mbid: String) :
    BaseBrowseRequest<RecordingBrowseResponse, RecordingBrowseIncType, RecordingBrowseEntityType>(entityType, mbid) {

    override fun browse() = createJsonRetrofitService().browseRecording(buildParams())
}

enum class RecordingBrowseEntityType(val type: String) : BrowseEntityTypeInterface {
    ARTIST(ARTIST_ENTITY),
    COLLECTION(COLLECTION_ENTITY),
    RELEASE(RELEASE_ENTITY);

    override fun toString() = type
}

enum class RecordingBrowseIncType(val inc: String) : BrowseIncTypeInterface {
    ALIASES(ALIASES_INC),
    ANNOTATION(ANNOTATION_INC),
    GENRES(GENRES_INC),
    TAGS(TAGS_INC),
    RATINGS(RATINGS_INC),
    USER_GENRES(USER_GENRES_INC),     //require authorization
    USER_TAGS(USER_TAGS_INC),         //require authorization
    USER_RATINGS(USER_RATINGS_INC),   //require authorization
    // TODO: ISRCS
    /*
     conflict with return type isrcs field
     search: https://musicbrainz.org/ws/2/recording?fmt=json&query=isrc:USGF19942501
     lookup: https://musicbrainz.org/ws/2/recording/6da76448-982a-4a01-b65b-9a710301c9c9?fmt=json&inc=isrcs
     search return array of objects with fields id = isrc
     lookup ISRCS return array of String isrc
     */
    //ISRCS(ISRCS_INC),
    ARTIST_CREDITS(ARTIST_CREDITS_INC);

    override fun toString() = inc
}