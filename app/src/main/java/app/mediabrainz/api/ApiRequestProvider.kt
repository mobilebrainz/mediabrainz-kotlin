package app.mediabrainz.api

import app.mediabrainz.api.searchrequest.*


object ApiRequestProvider {

    fun createAnnotationSearchRequest() = AnnotationSearchRequest()
    fun createAreaSearchRequest() = AreaSearchRequest()
    fun createArtistSearchRequest() = ArtistSearchRequest()
    fun createRecordingSearchRequest() = RecordingSearchRequest()
    fun createReleaseGroupSearchRequest() = ReleaseGroupSearchRequest()
    fun createCDStubSearchRequest() = CDStubSearchRequest()
    fun createEventSearchRequest() = EventSearchRequest()
    fun createInstrumentSearchRequest() = InstrumentSearchRequest()
    fun createLabelSearchRequest() = LabelSearchRequest()

}