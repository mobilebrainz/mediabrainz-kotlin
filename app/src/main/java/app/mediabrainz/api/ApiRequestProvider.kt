package app.mediabrainz.api

import app.mediabrainz.api.browserequest.*
import app.mediabrainz.api.lookuprequest.*
import app.mediabrainz.api.oauth.OAuthRequest
import app.mediabrainz.api.searchrequest.*


object ApiRequestProvider {

    fun createOAuthRequest() = OAuthRequest()

    fun createAnnotationSearchRequest() = AnnotationSearchRequest()
    fun createAreaSearchRequest() = AreaSearchRequest()
    fun createArtistSearchRequest() = ArtistSearchRequest()
    fun createCDStubSearchRequest() = CDStubSearchRequest()
    fun createEventSearchRequest() = EventSearchRequest()
    fun createInstrumentSearchRequest() = InstrumentSearchRequest()
    fun createLabelSearchRequest() = LabelSearchRequest()
    fun createPlaceSearchRequest() = PlaceSearchRequest()
    fun createRecordingSearchRequest() = RecordingSearchRequest()
    fun createReleaseGroupSearchRequest() = ReleaseGroupSearchRequest()
    fun createReleaseSearchRequest() = ReleaseSearchRequest()
    fun createSeriesSearchRequest() = SeriesSearchRequest()
    fun createTagSearchRequest() = TagSearchRequest()
    fun createUrlSearchRequest() = UrlSearchRequest()
    fun createWorkSearchRequest() = WorkSearchRequest()

    fun createAreaLookupRequest(mbid: String) = AreaLookupRequest(mbid)
    fun createArtistLookupRequest(mbid: String) = ArtistLookupRequest(mbid)
    fun createCollectionLookupRequest(mbid: String) = CollectionLookupRequest(mbid)
    fun createDiscidLookupRequest(mbid: String) = DiscidLookupRequest(mbid)
    fun createEventLookupRequest(mbid: String) = EventLookupRequest(mbid)
    fun createInstrumentLookupRequest(mbid: String) = InstrumentLookupRequest(mbid)
    fun createISRCLookupRequest(mbid: String) = ISRCLookupRequest(mbid)
    fun createISWCLookupRequest(mbid: String) = ISWCLookupRequest(mbid)
    fun createLabelLookupRequest(mbid: String) = LabelLookupRequest(mbid)
    fun createPlaceLookupRequest(mbid: String) = PlaceLookupRequest(mbid)
    fun createRecordingLookupRequest(mbid: String) = RecordingLookupRequest(mbid)
    fun createReleaseGroupLookupRequest(mbid: String) = ReleaseGroupLookupRequest(mbid)
    fun createReleaseLookupRequest(mbid: String) = ReleaseLookupRequest(mbid)
    fun createSeriesLookupRequest(mbid: String) = SeriesLookupRequest(mbid)
    fun createUrlLookupRequest(mbid: String) = UrlLookupRequest(mbid)
    fun createWorkLookupRequest(mbid: String) = WorkLookupRequest(mbid)

    fun createAreaBrowseRequest(entityType: AreaBrowseEntityType, mbid: String) =
        AreaBrowseRequest(entityType, mbid)

    fun createArtistBrowseRequest(entityType: ArtistBrowseEntityType, mbid: String) =
        ArtistBrowseRequest(entityType, mbid)

    fun createCollectionBrowseRequest(entityType: CollectionBrowseEntityType, mbid: String) =
        CollectionBrowseRequest(entityType, mbid)

    fun createEventBrowseRequest(entityType: EventBrowseEntityType, mbid: String) =
        EventBrowseRequest(entityType, mbid)

    fun createLabelBrowseRequest(entityType: LabelBrowseEntityType, mbid: String) =
        LabelBrowseRequest(entityType, mbid)

    fun createPlaceBrowseRequest(entityType: PlaceBrowseEntityType, mbid: String) =
        PlaceBrowseRequest(entityType, mbid)

    fun createRecordingBrowseRequest(entityType: RecordingBrowseEntityType, mbid: String) =
        RecordingBrowseRequest(entityType, mbid)

    fun createReleaseBrowseRequest(entityType: ReleaseBrowseEntityType, mbid: String) =
        ReleaseBrowseRequest(entityType, mbid)

    fun createReleaseGroupBrowseRequest(entityType: ReleaseGroupBrowseEntityType, mbid: String) =
        ReleaseGroupBrowseRequest(entityType, mbid)

    fun createSeriesBrowseRequest(entityType: SeriesBrowseEntityType, mbid: String) =
        SeriesBrowseRequest(entityType, mbid)

    fun createWorkBrowseRequest(entityType: WorkBrowseEntityType, mbid: String) =
        WorkBrowseRequest(entityType, mbid)

    fun createUrlkBrowseRequest(entityType: UrlBrowseEntityType, mbid: String) =
        UrlBrowseRequest(entityType, mbid)

}