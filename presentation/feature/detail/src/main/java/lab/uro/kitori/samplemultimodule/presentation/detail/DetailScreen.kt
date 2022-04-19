package lab.uro.kitori.samplemultimodule.presentation.detail

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import lab.uro.kitori.samplemultimodule.presentation.detail.item.DetailScreenItem
import lab.uro.kitori.samplemultimodule.presentation.ui.data.ScreenState
import lab.uro.kitori.samplemultimodule.presentation.ui.dialog.ErrorDialog
import lab.uro.kitori.samplemultimodule.presentation.ui.screen.AppScreen
import lab.uro.kitori.samplemultimodule.presentation.ui.screen.LoadingScreen

@Composable
fun DetailScreen(
    darkTheme: Boolean = isSystemInDarkTheme(),
    detailViewModel: DetailViewModel = viewModel(),
    name: String
) {
    val state by detailViewModel.state.observeAsState()
    @Suppress("UnnecessaryVariable") val fixedState = state

    AppScreen(
        darkTheme = darkTheme,
        title = stringResource(id = R.string.detail_title)
    ) {
        when (fixedState) {
            is ScreenState.Init -> detailViewModel.onShowScreen(name)
            is ScreenState.Loading -> LoadingScreen()
            is ScreenState.ShowContent<*> -> DetailScreenShowContent(fixedState.data as DetailScreenItem)
            is ScreenState.Error -> ErrorDialog(fixedState.errorMessage) {
                detailViewModel.onDismissError()
            }
            else -> {
                // nothing
            }
        }
    }
}

@Composable
fun DetailScreenShowContent(
    item: DetailScreenItem
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column {
            Text(text = item.name)
            Spacer(modifier = Modifier.height(32.dp))
            Text(text = item.url)
        }
    }
}
