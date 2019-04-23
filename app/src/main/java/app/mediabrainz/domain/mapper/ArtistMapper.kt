package app.mediabrainz.domain.mapper

import app.mediabrainz.api.response.ArtistResponse
import app.mediabrainz.api.response.TagResponse
import app.mediabrainz.domain.model.Artist
import app.mediabrainz.domain.model.ArtistType
import app.mediabrainz.domain.model.Tag


class ArtistMapper {

    fun mapTo(response: ArtistResponse) = with(response) {
        val artist = Artist(
            mbid,
            name,
            disambiguation ?: "",
            ArtistType.typeOf(type ?: ""),
            country ?: "",
            gender ?: "",
            if (area != null) AreaMapper().mapTo(area) else null,
            if (beginArea != null) AreaMapper().mapTo(beginArea) else null,
            if (endArea != null) AreaMapper().mapTo(endArea) else null,
            LifeSpanMapper().mapTo(lifeSpan),
            RatingMapper().mapTo(rating),
            RatingMapper().mapTo(userRating),
            PageMapper<TagResponse, Tag> { TagMapper().mapTo(it) }.mapToList(tags),
            PageMapper<TagResponse, Tag> { TagMapper().mapTo(it) }.mapToList(userTags),
            PageMapper<TagResponse, Tag> { TagMapper().mapTo(it) }.mapToList(genres),
            PageMapper<TagResponse, Tag> { TagMapper().mapTo(it) }.mapToList(userGenres)
        )
        artist
    }

}