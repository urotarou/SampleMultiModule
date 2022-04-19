package lab.uro.kitori.samplemultimodule.presentation.ui.screen

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import lab.uro.kitori.samplemultimodule.presentation.ui.theme.SampleMultiModuleTheme

@Composable
fun AppScreen(
    darkTheme: Boolean = isSystemInDarkTheme(),
    title: String = "",
    content: @Composable () -> Unit = {}
) {
    SampleMultiModuleTheme(darkTheme) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = title,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                )
            }
        ) {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                content()
            }
        }
    }
}
