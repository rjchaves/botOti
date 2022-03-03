package es.rjchav

class YoutubeSearchResult(val items: java.util.List[YoutubeResult])

class YoutubeResult(val id: Id)

class Id(val kind: String, val videoId: String)
