package arcanegolem.acolyte.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import arcanegolem.acolyte.ui.theme.BubbleAttachmentToTextPadding
import arcanegolem.acolyte.ui.theme.BubbleCornerRadius
import arcanegolem.acolyte.ui.theme.BubbleText
import arcanegolem.acolyte.ui.theme.HorizontalBubbleTextPadding
import arcanegolem.acolyte.ui.theme.VerticalBubbleTextPadding

@Composable
fun BubbleText(
  isIncoming : Boolean,
  text : String,
  attachment : Any?
) {
  Text(
    modifier = Modifier.padding(
      start = if (isIncoming) HorizontalBubbleTextPadding + BubbleCornerRadius else HorizontalBubbleTextPadding,
      top = if (attachment == null) VerticalBubbleTextPadding else BubbleAttachmentToTextPadding,
      end = if (isIncoming) HorizontalBubbleTextPadding else HorizontalBubbleTextPadding + BubbleCornerRadius
    ),
    text = text,
    color = BubbleText
  )
}