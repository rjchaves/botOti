package es.rjchav

import org.jsoup.Jsoup
import org.jsoup.nodes.Document

object Tidal {
  def getTidalMusic(url: String): String = {
    val doc: Document = Jsoup.connect(url).get()
    val musicName = doc.select("h1 > a").text()
    val artistName = doc.select(  "div.artist-list:nth-child(2) > a").text()
    musicName+" "+ artistName
  }
}
