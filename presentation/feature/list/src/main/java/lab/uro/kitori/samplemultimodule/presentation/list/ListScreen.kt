package lab.uro.kitori.samplemultimodule.presentation.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import lab.uro.kitori.samplemultimodule.presentation.list.item.ListScreenItem
import lab.uro.kitori.samplemultimodule.presentation.ui.data.ScreenState
import lab.uro.kitori.samplemultimodule.presentation.ui.dialog.ErrorDialog
import lab.uro.kitori.samplemultimodule.presentation.ui.screen.AppScreen
import lab.uro.kitori.samplemultimodule.presentation.ui.screen.EmptyScreen
import lab.uro.kitori.samplemultimodule.presentation.ui.screen.LoadingScreen

@Composable
fun ListScreen(
    darkTheme: Boolean = isSystemInDarkTheme(),
    listViewModel: ListViewModel = viewModel()
) {
    val state by listViewModel.state.observeAsState()
    @Suppress("UnnecessaryVariable") val fixedState = state

    AppScreen(
        darkTheme = darkTheme,
        title = stringResource(id = R.string.list_title)
    ) {
        when (fixedState) {
            is ScreenState.Init -> listViewModel.onShowScreen()
            is ScreenState.Loading -> LoadingScreen()
            is ScreenState.ShowContent<*> -> ListScreenShowContent(
                listViewModel,
                fixedState.data as ListScreenItem
            )
            is ScreenState.Empty -> EmptyScreen(
                message = stringResource(id = R.string.list_empty_message)
            )
            is ScreenState.Error -> ErrorDialog(fixedState.errorMessage) {
                listViewModel.onDismissError()
            }
            else -> {
                // nothing
            }
        }
    }
}

@Composable
fun ListScreenShowContent(
    listViewModel: ListViewModel,
    item: ListScreenItem
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(item.users) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 48.dp)
                    .clickable {
                        listViewModel.onClickItem(it.name)
                    },
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(text = it.name)
                }
                Divider()
            }
        }
    }
}
