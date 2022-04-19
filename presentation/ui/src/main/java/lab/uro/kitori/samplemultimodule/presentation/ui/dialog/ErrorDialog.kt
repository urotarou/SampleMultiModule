package lab.uro.kitori.samplemultimodule.presentation.ui.dialog

import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

@Composable
fun ErrorDialog(errorMessage: String, onClick: () -> Unit) {
    AlertDialog(
        onDismissRequest = { onClick() },
        text = { Text(text = errorMessage) },
        confirmButton = {
            TextButton(onClick = onClick) {
                Text(text = stringResource(id = android.R.string.ok))
            }
        }
    )
}
