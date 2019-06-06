package app.mediabrainz.domain.mapper

import app.mediabrainz.api.response.ArtistCreditResponse
import app.mediabrainz.api.response.ReleaseGroupResponse
import app.mediabrainz.api.response.ReleaseResponse
import app.mediabrainz.api.response.TagResponse
import app.mediabrainz.domain.model.*


class ReleaseGroupMapper {

    fun mapTo(response: ReleaseGroupResponse): ReleaseGroup =
        with(response) {
            val sTypes: ArrayList<RGSecondaryType> = ArrayList()
            secondaryTypes?.let {
                for (secondaryType in secondaryTypes) {
                    RGSecondaryType.typeOf(secondaryType)?.let { sTypes.add(it) }
                }
            }

            val rg = ReleaseGroup(
                mbid,
                title,
                RGPrimaryType.typeOf(primaryType),
                sTypes,
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

            TagMapper().filterTagged(rg)

            return rg
        }

}