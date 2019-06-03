package app.mediabrainz.domain.mapper

import app.mediabrainz.api.lookupbrowse.BaseLookupEntity
import app.mediabrainz.api.response.RelationResponse
import app.mediabrainz.domain.model.*


class RelationMapper(baseLookupEntity: BaseLookupEntity) {


    val areaRels = ArrayList<Relation<Area>>()
    val artistRels = ArrayList<Relation<Artist>>()
    val eventRels = ArrayList<Relation<Event>>()
    val instrumentRels = ArrayList<Relation<Instrument>>()
    val labelRels = ArrayList<Relation<Label>>()
    val placeRels = ArrayList<Relation<Place>>()
    val recordingRels = ArrayList<Relation<Recording>>()
    val releaseRels = ArrayList<Relation<Release>>()
    val releaseGroupRels = ArrayList<Relation<ReleaseGroup>>()
    val workRels = ArrayList<Relation<Work>>()
    val seriesRels = ArrayList<Relation<Series>>()
    val urlRels = ArrayList<Relation<Url>>()

    init {
        val relations = baseLookupEntity.relations
        if (!relations.isNullOrEmpty()) {
            for (relationResponse in relations) {
                with(relationResponse) {
                    when {
                        artist != null -> {
                            val relation = Relation(ArtistMapper().mapTo(artist))
                            mapRelationArgs(relation, relationResponse)
                            artistRels.add(relation)
                        }
                        area != null -> {
                            val relation = Relation(AreaMapper().mapTo(area))
                            mapRelationArgs(relation, relationResponse)
                            areaRels.add(relation)
                        }
                        event != null -> {
                            val relation = Relation(EventMapper().mapTo(event))
                            mapRelationArgs(relation, relationResponse)
                            eventRels.add(relation)
                        }
                        instrument != null -> {
                            val relation = Relation(InstrumentMapper().mapTo(instrument))
                            mapRelationArgs(relation, relationResponse)
                            instrumentRels.add(relation)
                        }
                        label != null -> {
                            val relation = Relation(LabelMapper().mapTo(label))
                            mapRelationArgs(relation, relationResponse)
                            labelRels.add(relation)
                        }
                        place != null -> {
                            val relation = Relation(PlaceMapper().mapTo(place))
                            mapRelationArgs(relation, relationResponse)
                            placeRels.add(relation)
                        }
                        recording != null -> {
                            val relation = Relation(RecordingMapper().mapTo(recording))
                            mapRelationArgs(relation, relationResponse)
                            recordingRels.add(relation)
                        }
                        release != null -> {
                            val relation = Relation(ReleaseMapper().mapTo(release))
                            mapRelationArgs(relation, relationResponse)
                            releaseRels.add(relation)
                        }
                        releaseGroup != null -> {
                            val relation = Relation(ReleaseGroupMapper().mapTo(releaseGroup))
                            mapRelationArgs(relation, relationResponse)
                            releaseGroupRels.add(relation)
                        }
                        series != null -> {
                            val relation = Relation(SeriesMapper().mapTo(series))
                            mapRelationArgs(relation, relationResponse)
                            seriesRels.add(relation)
                        }
                        work != null -> {
                            val relation = Relation(WorkMapper().mapTo(work))
                            mapRelationArgs(relation, relationResponse)
                            workRels.add(relation)
                        }
                        url != null -> {
                            val relation = Relation(UrlMapper().mapTo(url))
                            mapRelationArgs(relation, relationResponse)
                            urlRels.add(relation)
                        }
                        else -> {}
                    }
                }
            }
        }
    }

    private fun <T> mapRelationArgs(relation: Relation<T>, response: RelationResponse) {
        with(response) {
            relation.type = type
            relation.begin = begin ?: ""
            relation.end = end ?: ""
            relation.ended = ended ?: false
            relation.attributes = attributes ?: ArrayList()
        }
    }

}