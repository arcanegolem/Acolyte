package arcanegolem.acolyte.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import arcanegolem.acolyte.shapes.ChatBubbleIncomingWithTailShape
import arcanegolem.acolyte.shapes.ChatBubbleOutgoingNoTailShape
import arcanegolem.acolyte.shapes.ChatBubbleOutgoingWithTailShape

@Composable
fun ChatBubble(
  isIncoming : Boolean,
  tipSize : Dp,
  cornerRadius : Dp,
  messageText : String,
  bubbleColor : Color,
  textColor : Color
) {
  Row (
    modifier = Modifier.fillMaxWidth(),
    horizontalArrangement = if (isIncoming) Arrangement.Start else Arrangement.End
  ) {
    Row(
      modifier = Modifier.fillMaxWidth(0.90f),
      horizontalArrangement = if (isIncoming) Arrangement.Start else Arrangement.End
    ) {
      Column(
        modifier = Modifier
          .clip(
            if (isIncoming) ChatBubbleIncomingWithTailShape(cornerRadius, tipSize)
            else ChatBubbleOutgoingWithTailShape(cornerRadius, tipSize)
          )
          .background(bubbleColor)
          .padding(
            start = if (isIncoming) 15.dp else 10.dp,
            bottom = 15.dp,
            end = if (isIncoming) 10.dp else 15.dp,
            top = 5.dp
          )
      ) {
        Text(
          text = messageText,
          color = textColor
        )
      }
    }
  }
}