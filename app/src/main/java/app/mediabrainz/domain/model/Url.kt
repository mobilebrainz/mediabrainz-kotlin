package app.mediabrainz.domain.model


class Url(val mbid: String, val resource: String): Entity() {

    fun getPrettyUrl(): String {
        // Remove http:// and trailing /
        var url = resource.replace("http://", "")
        url = url.replace("https://", "")
        if (url.endsWith("/")) {
            url = url.substring(0, url.length - 1)
        }
        return url
    }
}
