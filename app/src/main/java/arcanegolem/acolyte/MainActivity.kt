package arcanegolem.acolyte

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import arcanegolem.acolyte.components.ChatBubbleLayout
import arcanegolem.acolyte.data.mockMessages
import arcanegolem.acolyte.shapes.ChatBubbleIncomingNoTailShape
import arcanegolem.acolyte.shapes.ChatBubbleIncomingWithTailShape
import arcanegolem.acolyte.shapes.ChatBubbleOutgoingNoTailShape
import arcanegolem.acolyte.shapes.ChatBubbleOutgoingWithTailShape
import arcanegolem.acolyte.ui.theme.AcolyteTheme
import coil.compose.AsyncImage
import coil.request.ImageRequest

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()

    val messages = mockMessages(100)

    setContent {
      AcolyteTheme {
        val lazyState = rememberLazyListState()
        val cornerRadius = 10.dp
        val tipSize = 5.dp

        LazyColumn(
          state = lazyState,
          modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray),
          verticalArrangement = Arrangement.Bottom,
          reverseLayout = true
        ) {
          itemsIndexed(messages) { index, message ->
            Spacer(modifier = Modifier.height(if (index == 0) 40.dp else if (messages[index - 1].isIncoming == message.isIncoming) 2.dp else 12.dp ))
            val isPreviousSame = if (index == 0) false else messages[index - 1].isIncoming == message.isIncoming
            val textColor = Color.Black
            ChatBubbleLayout(
              messageText = {
                if (message.text.isNotEmpty()){
                  Text(
                    modifier = Modifier.padding(
                      start = if (message.isIncoming) cornerRadius + tipSize else cornerRadius,
                      top = if (message.attachment == null) cornerRadius else 5.dp,
                      end = if (message.isIncoming) cornerRadius else cornerRadius + tipSize
                    ),
                    text = message.text,
                    color = textColor
                  )
                }
              },
              messageTime = {
                Text(
                  modifier = Modifier.padding(
                    start = if (message.isIncoming) cornerRadius + tipSize else cornerRadius,
                    bottom = maxOf(cornerRadius, tipSize),
                    end = if (message.isIncoming) cornerRadius else cornerRadius + tipSize
                  ),
                  text = message.deliveryTime,
                  color = textColor
                )
              },
              messageAttachment = {
                if (message.attachment != null){
                  AsyncImage(
                    modifier = Modifier
                      .fillMaxSize()
                      .padding(
                        start = if (message.isIncoming) cornerRadius + tipSize else cornerRadius,
                        top = cornerRadius,
                        end = if (message.isIncoming) cornerRadius else cornerRadius + tipSize
                      )
                      .clip(RoundedCornerShape(cornerRadius)),
                    contentScale = ContentScale.FillWidth,
                    model = ImageRequest.Builder(LocalContext.current)
                      .data(message.attachment)
                      .crossfade(true)
                      .build(),
                    contentDescription = null,
                  )
                }
              },
              bubbleShape = if (message.isIncoming) {
                if (isPreviousSame) ChatBubbleIncomingNoTailShape(cornerRadius, tipSize)
                else ChatBubbleIncomingWithTailShape(cornerRadius, tipSize)
              } else {
                if (isPreviousSame) ChatBubbleOutgoingNoTailShape(cornerRadius, tipSize)
                else ChatBubbleOutgoingWithTailShape(cornerRadius, tipSize)
              },
              bubbleColor = if (message.isIncoming) Color.LightGray else Color.Green,
              bubbleArrangement = if (message.isIncoming) Arrangement.Start else Arrangement.End
            )
            Spacer(Modifier.height(if (index == messages.lastIndex) 40.dp else 0.dp))
          }
        }
      }
    }
  }
}