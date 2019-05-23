package app.mediabrainz.ui.viewmodel.searchRepository

import app.mediabrainz.domain.model.RGType
import app.mediabrainz.domain.model.ReleaseGroup
import app.mediabrainz.domain.repository.searchRepository.ReleaseGroupSearchRepository


class ReleaseGroupSearchViewModel(val repo: ReleaseGroupSearchRepository = ReleaseGroupSearchRepository()) :
    BaseSearchViewModel<ReleaseGroup>(repo) {

    private var artistQuery: String = ""

    override fun search(query: String) {
        artistQuery = ""
        super.search(query)
    }

    fun search(artist: String, query: String) {
        if (result.value == null || this.artistQuery != artist || this.query != query) {
            this.artistQuery = artist
            this.query = query
            search()
        }
    }

    override fun search() {
        repo.search(result, artistQuery, query)
    }


    //todo: make distinct OfficialReleaseGroupSearchViewModel

    private var artistMbid: String? = null
    private var rgType: RGType? = null
    private var offset: Int = -1
    private var limit: Int = -1

    fun searchOfficialReleaseGroups(
        artistMbid: String,
        rgType: RGType,
        offset: Int, limit: Int
    ) {
        if (result.value == null
            || this.artistMbid != artistMbid
            || this.rgType != rgType
            || this.offset != offset || this.limit != limit
        ) {
            this.artistMbid = artistMbid
            this.rgType = rgType
            this.offset = offset
            this.limit = limit
            searchOfficialReleaseGroups()
        }
    }

    fun searchOfficialReleaseGroups() {
        if (artistMbid != null && rgType != null && offset != -1 && limit != -1) {
            repo.searchOfficialReleaseGroups(result, artistMbid!!, rgType!!, offset, limit)
        }
    }

}