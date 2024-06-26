package arcanegolem.acolyte

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import arcanegolem.acolyte.components.ChatBubble
import arcanegolem.acolyte.shapes.ChatBubbleIncomingNoTailShape
import arcanegolem.acolyte.shapes.ChatBubbleIncomingWithTailShape
import arcanegolem.acolyte.shapes.ChatBubbleOutgoingNoTailShape
import arcanegolem.acolyte.shapes.ChatBubbleOutgoingWithTailShape
import arcanegolem.acolyte.ui.theme.AcolyteTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      val lazyState = rememberLazyListState()

      AcolyteTheme {
        LazyColumn(
          state = lazyState,
          modifier = Modifier
            .fillMaxSize()
            .padding(top = 40.dp, bottom = 40.dp),
          verticalArrangement = Arrangement.Bottom
        ) {
          items(1000) { index ->
            val isIncoming = Random.nextBoolean()
            Spacer(modifier = Modifier.height(12.dp))
            ChatBubble(
              isIncoming = isIncoming,
              tipSize = 5.dp,
              cornerRadius = 10.dp,
              messageText = "Some message. Component test $index",
              bubbleColor = if (isIncoming) Color.DarkGray else Color.Blue,
              textColor = Color.White
            )
          }
        }

        LaunchedEffect(key1 = Unit) {
          lazyState.scrollToItem(999)
        }
      }
    }
  }
}