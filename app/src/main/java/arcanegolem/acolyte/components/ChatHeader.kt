package arcanegolem.acolyte.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import arcanegolem.acolyte.R
import arcanegolem.acolyte.ui.theme.OnBackgroundIcon
import coil.compose.AsyncImage
import dev.chrisbanes.haze.HazeProgressive
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.HazeStyle
import dev.chrisbanes.haze.HazeTint
import dev.chrisbanes.haze.hazeChild

@Composable
fun ChatHeader(
  hazeState: HazeState
) {
  val statusBarPadding = WindowInsets.systemBars.asPaddingValues().calculateTopPadding()
  val interactionSource = remember { MutableInteractionSource() }

  Column(
    modifier = Modifier
      .fillMaxWidth()
      .hazeChild(
        state = hazeState,
        style = HazeStyle(
          backgroundColor = Color.Transparent,
          tint = HazeTint(Color.Transparent),
          blurRadius = 40.dp,
          noiseFactor = 0f
        )
      ) {
        progressive = HazeProgressive.verticalGradient(startIntensity = 1f, endIntensity = 1f)
      }
  ){
    Spacer(modifier = Modifier.height(statusBarPadding))
    Row(
      modifier = Modifier
        .fillMaxWidth(),
      verticalAlignment = Alignment.CenterVertically
    ) {
      Spacer(Modifier.width(12.dp))
      Image(
        painter = painterResource(R.drawable.custom_chevron_left),
        contentDescription = "Back",
        modifier = Modifier
          .clickable(interactionSource, null) {  },
        colorFilter = ColorFilter.tint(OnBackgroundIcon)
      )
      Spacer(Modifier.width(28.dp))
      AsyncImage(
        modifier = Modifier
          .size(40.dp)
          .clip(CircleShape)
          .clickable(interactionSource, null) {  },
        model = R.drawable.broken,
        contentScale = ContentScale.FillBounds,
        contentDescription = null
      )
      Spacer(Modifier.width(12.dp))
      NameContainer(name = "Ghoul SSS Rank")
      Spacer(Modifier.weight(1f))
      Image(painter = painterResource(R.drawable.more), contentDescription = "More")
      Spacer(Modifier.width(12.dp))
    }
    Spacer(modifier = Modifier.height(16.dp))
  }
}