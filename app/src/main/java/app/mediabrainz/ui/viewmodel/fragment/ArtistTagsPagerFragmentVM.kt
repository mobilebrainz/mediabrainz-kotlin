package app.mediabrainz.ui.viewmodel.fragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.mediabrainz.api.xml.entity.TagVoteType
import app.mediabrainz.api.xml.entity.UserTagXML
import app.mediabrainz.domain.model.Artist
import app.mediabrainz.domain.repository.Resource
import app.mediabrainz.domain.repository.xmlRepository.PostArtistTagsRepository


class ArtistTagsPagerFragmentVM : ViewModel() {

    val repository = PostArtistTagsRepository()

    val artistld = MutableLiveData<Artist>()
    val postTag = MutableLiveData<Resource<Boolean>>()
    val propagateTag = MutableLiveData<Resource<Boolean>>()


    fun postArtistTag(tag: String, voteType: TagVoteType) {
        artistld.value?.apply {
            repository.postArtistTags(postTag, mbid, UserTagXML(tag, voteType.type))
        }
    }

    fun propagateTagToAlbums(tag: String, voteType: TagVoteType) {
        artistld.value?.apply {

        }
    }



}