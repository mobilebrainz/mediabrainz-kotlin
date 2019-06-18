package app.mediabrainz.api.xml.entity

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Namespace
import org.simpleframework.xml.Root
import java.util.*


@Root(name = "metadata")
@Namespace(reference = "http://musicbrainz.org/ns/mmd-2.0#")
class MetadataXML {

    @field:Element(name = "user-rating", required = false)
    var userRating: Int? = null

    @field:Element(name = "message", required = false)
    var message: MessageXML? = null

    @field:ElementList(name = "user-tag-list", required = false)
    var userTags: List<UserTagXML>? = null

    @field:ElementList(name = "artist-list", required = false)
    var artists: List<ArtistXML>? = null

    @field:ElementList(name = "recording-list", required = false)
    var recordings: List<RecordingXML>? = null

    @field:ElementList(name = "release-group-list", required = false)
    var releases: List<ReleaseXML>? = null

    @field:ElementList(name = "release-list", required = false)
    var releaseGroups: List<ReleaseGroupXML>? = null

    fun addArtists(vararg artists: ArtistXML) {
        if (this.artists == null) this.artists = ArrayList()
        (this.artists as ArrayList).addAll(artists)
    }

    fun addRecordings(vararg recordings: RecordingXML) {
        if (this.recordings == null) this.recordings = ArrayList()
        (this.recordings as ArrayList).addAll(recordings)
    }

    fun addReleaseGroups(vararg releaseGroups: ReleaseGroupXML) {
        if (this.releaseGroups == null) this.releaseGroups = ArrayList()
        (this.releaseGroups as ArrayList).addAll(releaseGroups)
    }

    fun addReleases(vararg releases: ReleaseXML) {
        if (this.releases == null) this.releases = ArrayList()
        (this.releases as ArrayList).addAll(releases)
    }

}
