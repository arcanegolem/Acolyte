package arcanegolem.acolyte.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import arcanegolem.acolyte.ui.theme.BubbleCornerRadius
import arcanegolem.acolyte.ui.theme.BubbleTipSize
import arcanegolem.acolyte.ui.theme.DarkPurpleTimeOBubble
import arcanegolem.acolyte.ui.theme.HorizontalBubbleTextPadding
import arcanegolem.acolyte.ui.theme.PurpleTimeOBubble
import arcanegolem.acolyte.ui.theme.VerticalBubbleTextPadding

@Composable
fun DeliveryTime(
  isIncoming : Boolean,
  deliveryTime : String
) {
  Text(
    modifier = Modifier.padding(
      start = if (isIncoming) HorizontalBubbleTextPadding + BubbleCornerRadius else HorizontalBubbleTextPadding,
      bottom = maxOf(VerticalBubbleTextPadding, BubbleTipSize),
      end = if (isIncoming) HorizontalBubbleTextPadding else HorizontalBubbleTextPadding + BubbleCornerRadius
    ),
    text = deliveryTime,
    color = if (isIncoming) DarkPurpleTimeOBubble else PurpleTimeOBubble
  )
}