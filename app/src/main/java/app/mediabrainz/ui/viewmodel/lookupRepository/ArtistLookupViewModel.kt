package app.mediabrainz.ui.viewmodel.lookupRepository

import app.mediabrainz.domain.model.Artist
import app.mediabrainz.domain.repository.lookupRepository.ArtistLookupRepository


class ArtistLookupViewModel(val repo: ArtistLookupRepository = ArtistLookupRepository()) :
    BaseLookupViewModel<Artist>(repo) {

    override fun lookup() {
        repo.lookup(result, mbid)
    }

}