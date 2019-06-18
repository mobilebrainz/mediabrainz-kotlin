package app.mediabrainz.ui.viewmodel.activity

import androidx.lifecycle.ViewModel
import app.mediabrainz.api.xml.entity.TagVoteType
import app.mediabrainz.domain.model.Tagged
import app.mediabrainz.ui.core.viewmodel.SingleLiveEvent


class TaggedVM : ViewModel() {

    class TagVote(val tag: String, val voteType: TagVoteType)

    var tagged: Tagged? = null

    var postTag: SingleLiveEvent<TagVote> = SingleLiveEvent()

}