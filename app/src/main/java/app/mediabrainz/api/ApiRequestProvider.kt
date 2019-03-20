package app.mediabrainz.api

import app.mediabrainz.api.searchrequest.*


object ApiRequestProvider {

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
}