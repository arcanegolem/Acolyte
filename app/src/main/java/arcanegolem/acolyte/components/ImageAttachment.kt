package arcanegolem.acolyte.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import arcanegolem.acolyte.ui.theme.BubbleCornerRadius
import arcanegolem.acolyte.ui.theme.BubbleTipSize
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun ImageAttachment(
  isIncoming : Boolean,
  attachment : Any?
) {
  AsyncImage(
    modifier = Modifier
      .fillMaxSize()
      .padding(
        start = if (isIncoming) BubbleCornerRadius + BubbleTipSize else BubbleTipSize,
        top = BubbleTipSize,
        end = if (isIncoming) BubbleTipSize else BubbleCornerRadius + BubbleTipSize
      )
      .clip(RoundedCornerShape(BubbleCornerRadius)),
    contentScale = ContentScale.FillWidth,
    model = ImageRequest.Builder(LocalContext.current)
      .data(attachment)
      .crossfade(true)
      .build(),
    contentDescription = null,
  )
}