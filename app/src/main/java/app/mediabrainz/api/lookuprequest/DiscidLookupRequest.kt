package app.mediabrainz.api.lookuprequest

import app.mediabrainz.api.lookupbrowse.BaseLookupRequest
import app.mediabrainz.api.lookupbrowse.LookupParamType
import app.mediabrainz.api.response.DiscResponse
import app.mediabrainz.api.response.ReleaseGroupType

/**
 *
    DiscidLookupService("I5l9cCSFccLKFEKS.7wqSZAorPU-")
        .addToc("1+12+267257+150+22767+41887+58317+72102+91375+104652+115380+132165+143932+159870+174597")
        //.addRels(URL_RELS, ARTIST_RELS)
        .lookup();

    Passing "-" (or any invalid placeholder) as the discid will cause it to be ignored if a valid toc is present:
    DiscidLookupService("-")
        .addToc("1+12+267257+150+22767+41887+58317+72102+91375+104652+115380+132165+143932+159870+174597")
        //By default, fuzzy toc searches only return mediums whose format is set to "CD."
        //If you want to search all mediums regardless of format, add 'media-format=all'
        .addMediaFormat("all")
        .lookup();
 */
class DiscidLookupRequest(mbid: String) : BaseLookupRequest<DiscResponse, ReleaseLookupIncType>(mbid) {

    override fun lookup() = createJsonRetrofitService().lookupDisc(mbid, buildParams())

    fun addReleaseGroupType(type: ReleaseGroupType): DiscidLookupRequest {
        addParam(LookupParamType.TYPE, type.toString().toLowerCase())
        return this
    }

    fun addToc(toc: String): DiscidLookupRequest {
        addParam(LookupParamType.TOC, toc)
        return this
    }

    fun addMediaFormat(format: String): DiscidLookupRequest {
        addParam(LookupParamType.MEDIA_FORMAT, format)
        return this
    }
}
