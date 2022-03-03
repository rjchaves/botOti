package es.rjchav

import com.google.gson.GsonBuilder
import org.apache.http.HttpResponse
import org.apache.http.client.utils.URIBuilder
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.HttpClients

import org.apache.http.entity.ContentType

import java.io.InputStreamReader

class Youtube (key: String){
  def search(query: String) = {


    val httpclient = HttpClients.createDefault()

    val uri = new URIBuilder("https://www.googleapis.com/youtube/v3/search")
      .setParameter("q", query)
      .setParameter("key", key)
      .build()

    val get = new HttpGet(uri)
    httpclient.execute(get, (response: HttpResponse) => {
      val status = response.getStatusLine.getStatusCode
      if (status >= 200 && status < 300) {
        val entity = response.getEntity
        if (entity != null) {
          val gson = new GsonBuilder().create
          val contentType = ContentType.getOrDefault(entity)
          val charset = contentType.getCharset
          val reader = new InputStreamReader(entity.getContent, charset);
          gson fromJson(reader, classOf[YoutubeSearchResult])
        }
        else null
      }
      else null
    })
  }
}
