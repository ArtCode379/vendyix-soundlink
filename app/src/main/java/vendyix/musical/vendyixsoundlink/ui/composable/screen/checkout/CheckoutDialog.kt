package vendyix.musical.vendyixsoundlink.ui.composable.screen.checkout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import vendyix.musical.vendyixsoundlink.R

@Composable
fun CheckoutDialog(orderNumber: String, onConfirm: () -> Unit) {
    AlertDialog(
        onDismissRequest = onConfirm,
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text(stringResource(R.string.udlxj_checkout_dialog_confirm))
            }
        },
        title = { Text(stringResource(R.string.udlxj_checkout_dialog_title), style = MaterialTheme.typography.titleLarge) },
        text = {
            Column(Modifier.fillMaxWidth()) {
                Text(stringResource(R.string.udlxj_order_number, orderNumber), style = MaterialTheme.typography.titleMedium)
                Text(stringResource(R.string.udlxj_checkout_success_message))
            }
        },
    )
}
