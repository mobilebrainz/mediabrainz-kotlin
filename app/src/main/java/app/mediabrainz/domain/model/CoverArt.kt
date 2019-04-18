package app.mediabrainz.domain.model

import app.mediabrainz.api.response.CoverArtSize
import app.mediabrainz.api.response.CoverArtSize.*


class CoverArt(
    val mbid: String,
    val image: String,
    val types: List<String> = ArrayList(),
    val front: Boolean = false,
    val back: Boolean = false,
    val thumbnails: Thumbnails
) : Entity()


class Thumbnails(
    val i250: String = "",
    val i500: String = "",
    val i1200: String = "",
    val small: String = "",
    val large: String = ""
)

fun getFrontCoverArtImage(coverArts: List<CoverArt>, size: CoverArtSize = SMALL_SIZE): String {
    for (coverArt in coverArts) {
        with(coverArt) {
            if (front) {
                return when (size) {
                    SMALL_SIZE -> {
                        when {
                            thumbnails.i250.isNotEmpty() -> thumbnails.i250
                            thumbnails.small.isNotEmpty() -> thumbnails.small
                            else -> ""
                        }
                    }
                    LARGE_SIZE -> {
                        when {
                            thumbnails.i500.isNotEmpty() -> thumbnails.i500
                            thumbnails.large.isNotEmpty() -> thumbnails.large
                            else -> ""
                        }
                    }
                    ULTRA_LARGE_SIZE -> {
                        when {
                            thumbnails.i1200.isNotEmpty() -> thumbnails.i1200
                            else -> ""
                        }
                    }
                }
            }
        }
    }
    return ""
}

