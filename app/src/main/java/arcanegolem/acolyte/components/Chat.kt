package arcanegolem.acolyte.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import arcanegolem.acolyte.data.mockMessages
import arcanegolem.acolyte.shapes.ChatBubbleIncomingNoTailShape
import arcanegolem.acolyte.shapes.ChatBubbleIncomingSmoothWithTail
import arcanegolem.acolyte.shapes.ChatBubbleOutgoingNoTailShape
import arcanegolem.acolyte.shapes.ChatBubbleOutgoingSmoothWithTail
import arcanegolem.acolyte.ui.theme.BlackChatBG
import arcanegolem.acolyte.ui.theme.BubbleCornerRadius
import arcanegolem.acolyte.ui.theme.BubbleTipSize
import arcanegolem.acolyte.ui.theme.DarkPurpleIBubbleLeft
import arcanegolem.acolyte.ui.theme.PurpleOBubbleLeft
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.haze

@Composable
fun Chat() {
  val lazyState = rememberLazyListState()
  val messages = mockMessages(100)

  val hazeState = remember { HazeState() }

  Box(modifier = Modifier.fillMaxSize()){
    Box(Modifier.haze(hazeState)){
      LazyColumn(
        state = lazyState,
        modifier = Modifier
          .fillMaxSize()
          .background(BlackChatBG),
        verticalArrangement = Arrangement.Bottom,
        reverseLayout = true
      ) {
        itemsIndexed(messages) { index, message ->
          Spacer(modifier = Modifier.height(if (index == 0) 40.dp else if (messages[index - 1].isIncoming == message.isIncoming) 2.dp else 12.dp))
          val isPreviousSame =
            if (index == 0) false else messages[index - 1].isIncoming == message.isIncoming
          ChatBubbleLayout(
            messageText = {
              if (message.text.isNotEmpty()) {
                BubbleText(message.isIncoming, message.text, message.attachment)
              }
            },
            messageTime = { DeliveryTime(message.isIncoming, message.deliveryTime) },
            messageAttachment = {
              if (message.attachment != null) {
                ImageAttachment(message.isIncoming, message.attachment)
              }
            },
            bubbleShape = if (message.isIncoming) {
              if (isPreviousSame) ChatBubbleIncomingNoTailShape(BubbleCornerRadius, BubbleTipSize)
              else ChatBubbleIncomingSmoothWithTail(BubbleCornerRadius, BubbleTipSize)
            } else {
              if (isPreviousSame) ChatBubbleOutgoingNoTailShape(BubbleCornerRadius, BubbleTipSize)
              else ChatBubbleOutgoingSmoothWithTail(BubbleCornerRadius, BubbleTipSize)
            },
            bubbleColor = if (message.isIncoming) DarkPurpleIBubbleLeft else PurpleOBubbleLeft,
            bubbleArrangement = if (message.isIncoming) Arrangement.Start else Arrangement.End
          )
          Spacer(Modifier.height(if (index == messages.lastIndex) 40.dp else 0.dp))
        }
      }
    }

    ChatHeader(
      hazeState = hazeState
    )
  }
}