package arcanegolem.acolyte.shapes

import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp

class ChatBubbleIncomingSmoothWithTail(
  private val cornerRadius : Dp = 10.dp,
  private val tipSize : Dp = cornerRadius / 2
) : Shape {
  override fun createOutline(
    size: Size,
    layoutDirection: LayoutDirection,
    density: Density
  ): Outline {
    val tipSize = with(density) { tipSize.toPx() }
    val cornerRadius = with(density) { cornerRadius.toPx() }
    val path = Path().apply {
      addRoundRect(
        RoundRect(
          left = cornerRadius,
          top = 0f,
          right = size.width,
          bottom = size.height - tipSize,
          topLeftCornerRadius = CornerRadius(cornerRadius),
          topRightCornerRadius = CornerRadius(cornerRadius),
          bottomLeftCornerRadius = CornerRadius(tipSize),
          bottomRightCornerRadius = CornerRadius(cornerRadius)
        )
      )
    }

    return Outline.Generic(path)
  }
}