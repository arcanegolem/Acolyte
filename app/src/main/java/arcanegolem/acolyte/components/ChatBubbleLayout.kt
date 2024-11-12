package arcanegolem.acolyte.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import arcanegolem.acolyte.shapes.ChatBubbleIncomingWithTailShape
import arcanegolem.acolyte.shapes.ChatBubbleOutgoingWithTailShape

@Preview
@Composable
fun ChatBubblePreview() {
  val cornerRadius = 10.dp
  val tipSize = 5.dp

  Column(
    modifier = Modifier
      .width(500.dp)
      .height(500.dp)
  ) {
    ChatBubbleLayout(
      messageText = {
        Text(
          modifier = Modifier.padding(
            start = cornerRadius + tipSize,
            top = 5.dp,
            end = cornerRadius
          ),
          text = "Sample incoming bubble with tipSize $tipSize and cornerRadius $cornerRadius",
          color = Color.Black
        )
      },
      messageTime = {
        Text(
          modifier = Modifier.padding(
            start = cornerRadius + tipSize,
            bottom = maxOf(cornerRadius, tipSize),
            end = cornerRadius
          ),
          text = "22:22",
          color = Color.Black
        )
      },
      messageAttachment = {  },
      bubbleColor = Color.White,
      bubbleShape = ChatBubbleIncomingWithTailShape(cornerRadius, tipSize),
      bubbleArrangement = Arrangement.Start
    )

    ChatBubbleLayout(
      messageText = {
        Text(
          modifier = Modifier.padding(
            start = cornerRadius,
            top = 5.dp,
            end = cornerRadius + tipSize
          ),
          text = "Sample outgoing bubble with tipSize $tipSize and cornerRadius $cornerRadius",
          color = Color.Black
        )
      },
      messageTime = {
        Text(
          modifier = Modifier.padding(
            start = cornerRadius,
            bottom = maxOf(cornerRadius, tipSize),
            end = cornerRadius + tipSize
          ),
          text = "22:22",
          color = Color.Black
        )
      },
      messageAttachment = {  },
      bubbleColor = Color.Green,
      bubbleShape = ChatBubbleOutgoingWithTailShape(cornerRadius, tipSize),
      bubbleArrangement = Arrangement.End
    )
  }
}

@Composable
fun ChatBubbleLayout(
  messageText : @Composable () -> Unit = {},
  messageTime : @Composable () -> Unit,
  messageAttachment : @Composable () -> Unit = {},
  bubbleColor : Color,
  bubbleShape : Shape,
  bubbleArrangement : Arrangement.Horizontal
) {
  val screenWidth = with(LocalDensity.current){ LocalConfiguration.current.screenWidthDp.dp.toPx() }.toInt()

  Row(
    modifier = Modifier.fillMaxWidth(),
    horizontalArrangement = bubbleArrangement
  ){
    Layout(
      modifier = Modifier
        .clip(bubbleShape)
        .background(bubbleColor),
      contents = listOf(messageTime, messageText, messageAttachment),
      measurePolicy = { (messageTimeM, messageTextM, attachmentM), constraints ->
        val actualConstraints = constraints.copy(minWidth = 0, minHeight = 0, maxWidth = (screenWidth * 0.9f).toInt())

        val messageTimePlaceable : Placeable? = if (messageTimeM.isNotEmpty()) messageTimeM.first().measure(actualConstraints) else null
        val messageTextPlaceable : Placeable? = if (messageTextM.isNotEmpty()) messageTextM.first().measure(actualConstraints) else null
        val messageAttachmentPlaceable : Placeable? = if (attachmentM.isNotEmpty()) attachmentM.first().measure(actualConstraints) else null

        val messageTimeHeight = messageTimePlaceable?.height ?: 0
        val messageTextHeight = messageTextPlaceable?.height ?: 0
        val messageAttachmentHeight = messageAttachmentPlaceable?.height ?: 0

        val messageTimeWidth = messageTimePlaceable?.width ?: 0
        val messageTextWidth = messageTextPlaceable?.width ?: 0
        val messageAttachmentWidth = messageAttachmentPlaceable?.width ?: 0

        val height = messageTimeHeight + messageTextHeight + messageAttachmentHeight
        val width = maxOf(messageAttachmentWidth, maxOf(messageTextWidth, messageTimeWidth))

        val messageAttachmentOffset = IntOffset(0,0)
        val messageTextOffset = IntOffset(0, messageAttachmentHeight)
        val messageTimeOffset = IntOffset(width - messageTimeWidth, messageTextHeight + messageAttachmentHeight)

        layout(width, height) {
          messageAttachmentPlaceable?.place(messageAttachmentOffset)
          messageTextPlaceable?.place(messageTextOffset)
          messageTimePlaceable?.place(messageTimeOffset)
        }
      }
    )
  }
}