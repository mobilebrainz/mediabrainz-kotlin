package app.mediabrainz.domain.mapper

import app.mediabrainz.api.response.CoverArtImageResponse
import app.mediabrainz.api.response.ReleaseCoverArtResponse
import app.mediabrainz.domain.model.CoverArt


class CoverArtMapper {

    fun mapTo(response: CoverArtImageResponse) = with(response) {
        val coverArt = CoverArt(id)
        coverArt
    }

    fun mapToList(response: ReleaseCoverArtResponse): List<CoverArt> {
        val items = ArrayList<CoverArt>()
        response.images?.let {
            for (imageResponse in it) {
                items.add(mapTo(imageResponse))
            }
        }
        return items
    }

}