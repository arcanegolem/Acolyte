package arcanegolem.acolyte.shapes

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp

class ChatBubbleOutgoingWithTailShape(
  val cornerRadius: Dp = 10.dp,
  val tipSize: Dp = cornerRadius / 2
): Shape {

  override fun createOutline(
    size: Size,
    layoutDirection: LayoutDirection,
    density: Density
  ): Outline {
    val tipSize = with(density) { tipSize.toPx() }
    val cornerRadius = with(density) { cornerRadius.toPx() }
    val path = Path().apply {
      // Top-left angle
      arcTo(
        rect = Rect(
          Offset(
            0f, 0f
          ),
          Size(cornerRadius * 2, cornerRadius * 2)
        ),
        startAngleDegrees = 180f,
        sweepAngleDegrees = 90f,
        forceMoveTo = false
      )

      // To top-right
      lineTo(
        size.width - tipSize,
        0f
      )

      // Top-right angle
      arcTo(
        rect = Rect(
          Offset(
            size.width - tipSize - cornerRadius * 2,
            0f
          ),
          Size(cornerRadius * 2, cornerRadius * 2)
        ),
        startAngleDegrees = 270f,
        sweepAngleDegrees = 90f,
        forceMoveTo = false
      )

      // To bottom-right
      lineTo(
        size.width - tipSize,
        size.height - tipSize - cornerRadius
      )

      // To tip angle
      lineTo(
        size.width,
        size.height
      )

      // To tip root
      lineTo(
        size.width - cornerRadius - tipSize,
        y = size.height - tipSize
      )

      // To bottom-left
      lineTo(
        cornerRadius * 2,
        size.height - tipSize
      )

      // Bottom left angle
      arcTo(
        rect = Rect(
          Offset(
            0f,
            size.height - tipSize - cornerRadius * 2
          ),
          Size(cornerRadius * 2, cornerRadius * 2)
        ),
        startAngleDegrees = 90f,
        sweepAngleDegrees = 90f,
        forceMoveTo = false
      )

      close()
    }

    return Outline.Generic(path)
  }
}