package ir.training.currency.main.view.widgets.dialog.alert

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text


@Composable
fun AlertDialog(title: String, text: String, onDismissRequest: () -> Unit) {
    AlertDialog(
        title = {
            Text(text = title)
        },
        text = {
            Text(text = text)
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text("Confirm")
            }
        },
    )
}