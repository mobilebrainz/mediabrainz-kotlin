package app.mediabrainz.domain.model

import androidx.annotation.StringRes
import app.mediabrainz.api.response.ArtistsRelationType
import app.mediabrainz.ui.R


enum class ArtistArtistRelationshipType(
    val type: ArtistsRelationType,
    @StringRes val tabRes: Int,
    @StringRes val relationRes: Int,
    @StringRes val descriptionRes: Int
) {
    CURRENT_MEMBER_OF_BANDS(
        ArtistsRelationType.MEMBER_OF_BAND,
        R.string.tab_current_member,
        R.string.rel_current_member_of_band,
        R.string.rel_current_member_of_band_description
    ),
    PAST_MEMBER_OF_BANDS(
        ArtistsRelationType.MEMBER_OF_BAND,
        R.string.tab_past_member,
        R.string.rel_past_member_of_band,
        R.string.rel_past_member_of_band_description
    ),
    SUBGROUPS(
        ArtistsRelationType.SUBGROUP,
        R.string.tab_subgroup,
        R.string.rel_subgroup,
        R.string.rel_subgroup_description
    ),
    CONDUCTOR_POSITIONS(
        ArtistsRelationType.CONDUCTOR_POSITION,
        R.string.tab_conductor,
        R.string.rel_conductor_position,
        R.string.rel_conductor_position_description
    ),
    FOUNDERS(
        ArtistsRelationType.FOUNDER,
        R.string.tab_founder,
        R.string.rel_founder,
        R.string.rel_founder_description
    ),
    SUPPORTING_MUSICIANS(
        ArtistsRelationType.SUPPORTING_MUSICIAN,
        R.string.tab_supporting,
        R.string.rel_supporting_musician,
        R.string.rel_supporting_musician_description
    ),
    VOCAL_SUPPORTING_MUSICIANS(
        ArtistsRelationType.VOCAL_SUPPORTING_MUSICIAN,
        R.string.tab_vocal_supporting,
        R.string.rel_vocal_supporting_musician,
        R.string.rel_vocal_supporting_musician_description
    ),
    INSTRUMENTAL_SUPPORTING_MUSICIANS(
        ArtistsRelationType.INSTRUMENTAL_SUPPORTING_MUSICIAN,
        R.string.tab_instrumental_supporting,
        R.string.rel_instrumental_supporting_musician,
        R.string.rel_instrumental_supporting_musician_description
    ),
    TRIBUTES(
        ArtistsRelationType.TRIBUTE,
        R.string.tab_tribute,
        R.string.rel_tribute,
        R.string.rel_tribute_description
    ),
    VOICE_ACTORS(
        ArtistsRelationType.VOICE_ACTOR,
        R.string.tab_voice_actor,
        R.string.rel_voice_actor,
        R.string.rel_voice_actor_description
    ),
    COLLABORATIONS(
        ArtistsRelationType.COLLABORATION,
        R.string.tab_collaboration,
        R.string.rel_collaboration,
        R.string.rel_collaboration_description
    ),
    IS_PERSONS(
        ArtistsRelationType.IS_PERSON,
        R.string.tab_is_person,
        R.string.rel_is_person,
        R.string.rel_is_person_description
    ),
    TEACHERS(
        ArtistsRelationType.TEACHER,
        R.string.tab_teacher,
        R.string.rel_teacher,
        R.string.rel_teacher_description
    ),
    COMPOSER_IN_RESIDENCES(
        ArtistsRelationType.COMPOSER_IN_RESIDENCE,
        R.string.tab_composer,
        R.string.rel_composer_in_residence,
        R.string.rel_composer_in_residence_description
    );

}