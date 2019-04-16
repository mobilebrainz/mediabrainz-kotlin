package app.mediabrainz.domain.mapper

import app.mediabrainz.api.response.ArtistCreditResponse
import app.mediabrainz.api.response.ReleaseGroupResponse
import app.mediabrainz.api.response.ReleaseResponse
import app.mediabrainz.api.response.TagResponse
import app.mediabrainz.domain.model.ArtistCredit
import app.mediabrainz.domain.model.Release
import app.mediabrainz.domain.model.ReleaseGroup
import app.mediabrainz.domain.model.Tag


class ReleaseGroupMapper {

    fun mapTo(response: ReleaseGroupResponse) = with(response) {
        val rg = ReleaseGroup(
            mbid,
            title,
            primaryType ?: "",
            secondaryTypes ?: ArrayList(),
            disambiguation ?: "",
            firstReleaseDate ?: "",
            PageMapper<ReleaseResponse, Release> { ReleaseMapper().mapTo(it) }.mapToList(releases),
            PageMapper<ArtistCreditResponse, ArtistCredit> { ArtistCreditMapper().mapTo(it) }
                .mapToList(artistCredits),
            RatingMapper().mapTo(rating),
            RatingMapper().mapTo(userRating),
            PageMapper<TagResponse, Tag> { TagMapper().mapTo(it) }.mapToList(tags),
            PageMapper<TagResponse, Tag> { TagMapper().mapTo(it) }.mapToList(userTags),
            PageMapper<TagResponse, Tag> { TagMapper().mapTo(it) }.mapToList(genres),
            PageMapper<TagResponse, Tag> { TagMapper().mapTo(it) }.mapToList(userGenres)
        )
        rg
    }

}