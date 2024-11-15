package arcanegolem.acolyte

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import arcanegolem.acolyte.components.Chat
import arcanegolem.acolyte.ui.theme.AcolyteTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()

    setContent {
      AcolyteTheme {
        Chat()
      }
    }
  }
}