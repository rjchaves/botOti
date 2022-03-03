package es.rjchav

import discord4j.core.DiscordClient
import discord4j.core.event.domain.message.MessageCreateEvent



object Main {
  def main(args: Array[String]): Unit = {
    val discordKey = args(0)
    val googleKey = args(1)
    val client = DiscordClient.create(discordKey)
    val gateway = client.login.block

    gateway.on(classOf[MessageCreateEvent]).subscribe((event: MessageCreateEvent) => {
      def processEvent(event: MessageCreateEvent) = {
        val message = event.getMessage
        if (message.getContent.contains("tidal.com")) {
          val channel = message.getChannel.block
          val query = Tidal.getTidalMusic(message.getContent)
          val youtubeResult = new Youtube(googleKey).search(query)

          val value = youtubeResult.items.stream()
            .filter(i => i.id != null)
            .filter(i => i.id.kind != null)
            .filter(i => "youtube#video" == i.id.kind)
            .findFirst().map(i => i.id.videoId).orElse("mvYLovp5isw")
          val youtubeUrl = "https://www.youtube.com/watch?v=" + value
          channel.createMessage(youtubeUrl).block
        }
      }

      processEvent(event)
    })

    gateway.onDisconnect.block
  }
}
