package app.mediabrainz.domain.mapper

import app.mediabrainz.api.response.CoverArtImageResponse
import app.mediabrainz.api.response.ReleaseCoverArtResponse
import app.mediabrainz.api.response.ThumbnailsResponse
import app.mediabrainz.domain.model.CoverArt
import app.mediabrainz.domain.model.Thumbnails


class CoverArtMapper {

    fun mapTo(response: CoverArtImageResponse): CoverArt =
        with(response) {
            CoverArt(
                id,
                image,
                types ?: ArrayList(),
                front ?: false,
                back ?: false,
                mapThumbnails(thumbnails)
            )
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

    private fun mapThumbnails(response: ThumbnailsResponse?): Thumbnails {
        var thumbnails = Thumbnails()
        response?.apply {
            thumbnails = Thumbnails(
                i250 ?: "",
                i500 ?: "",
                i1200 ?: "",
                small ?: "",
                large ?: ""
            )
        }
        return thumbnails
    }

}