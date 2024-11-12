package arcanegolem.acolyte.data

import arcanegolem.acolyte.R
import java.text.SimpleDateFormat
import java.util.Locale
import kotlin.random.Random

private val sampleAttachments = listOf(null, R.drawable.landscape, R.drawable.blbl, R.drawable.broken, null, null, null, null)
private val sdf = SimpleDateFormat("HH:mm", Locale.ROOT)

fun mockMessages(amount : Int) : List<Message> {
  val messages = mutableListOf<Message>()

  repeat(amount) {
    val attachment = sampleAttachments[Random.nextInt(0, sampleAttachments.size)]
    messages.add(
      Message(
        text = if (attachment == R.drawable.landscape) "" else getRandomString(Random.nextInt(200)),
        isIncoming = Random.nextBoolean(),
        attachment = attachment,
        deliveryTime = sdf.format(System.currentTimeMillis())
      )
    )
  }
  messages.reverse()

  return messages
}

private fun getRandomString(length: Int) : String {
  val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
  return (1..length)
    .map { allowedChars.random() }
    .joinToString("")
}