package arcanegolem.acolyte.data

data class Message(
  val text: String,
  val attachment: Any? = null,
  val isIncoming: Boolean,
  val deliveryTime: String
) {

}