package arcanegolem.acolyte.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import arcanegolem.acolyte.ui.theme.DarkPurpleNameContainerBG

@Composable
fun NameContainer(name : String) {
  Row(
    modifier = Modifier
      .clip(RoundedCornerShape(100))
      .background(DarkPurpleNameContainerBG)
  ) {
    Text(
      modifier = Modifier.padding(horizontal = 16.dp, vertical = 2.dp),
      text = name,
      color = Color.White,
      fontWeight = FontWeight.SemiBold,
      fontSize = 12.sp
    )
  }
}