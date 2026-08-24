package vendyix.musical.vendyixsoundlink.ui.composable.screen.checkout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel
import vendyix.musical.vendyixsoundlink.R
import vendyix.musical.vendyixsoundlink.ui.state.DataUiState
import vendyix.musical.vendyixsoundlink.ui.viewmodel.CheckoutViewModel

@Composable
fun CheckoutScreen(
    modifier: Modifier = Modifier,
    viewModel: CheckoutViewModel = koinViewModel(),
    onNavigateToOrdersScreen: () -> Unit,
) {
    val focusManager = LocalFocusManager.current
    val orderState by viewModel.orderState.collectAsStateWithLifecycle()
    val emailInvalid by viewModel.emailInvalidState.collectAsStateWithLifecycle()
    val enabled by remember {
        derivedStateOf {
            viewModel.customerFirstName.isNotBlank() &&
                viewModel.customerLastName.isNotBlank() &&
                viewModel.customerEmail.isNotBlank()
        }
    }
    val order = (orderState as? DataUiState.Populated)?.data
    if (order != null) {
        CheckoutDialog(order.orderNumber, onNavigateToOrdersScreen)
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Text(stringResource(R.string.udlxj_reserve_your_order), style = MaterialTheme.typography.headlineMedium)
        Text(stringResource(R.string.udlxj_checkout_intro), color = MaterialTheme.colorScheme.onSurfaceVariant)
        CheckoutTextField(viewModel.customerFirstName, viewModel::updateCustomerFirstName, stringResource(R.string.udlxj_checkout_text_field_first_name))
        CheckoutTextField(viewModel.customerLastName, viewModel::updateCustomerLastName, stringResource(R.string.udlxj_checkout_text_field_last_name))
        CheckoutTextField(
            input = viewModel.customerEmail,
            onInputChange = viewModel::updateCustomerEmail,
            labelText = stringResource(R.string.udlxj_checkout_text_field_email),
            isError = emailInvalid,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email, imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
        )
        if (emailInvalid) {
            Text(stringResource(R.string.udlxj_invalid_email), color = MaterialTheme.colorScheme.error)
        }
        Card(Modifier.fillMaxWidth()) {
            Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Text(stringResource(R.string.udlxj_pickup_title), style = MaterialTheme.typography.titleMedium)
                Text(stringResource(R.string.udlxj_pickup_24_hours), color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
        Button(
            onClick = viewModel::placeOrder,
            enabled = enabled,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(stringResource(R.string.udlxj_button_confirm_order_label))
        }
    }
}

@Composable
fun CheckoutTextField(
    input: String,
    onInputChange: (String) -> Unit,
    labelText: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isError: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
) {
    OutlinedTextField(
        value = input,
        onValueChange = onInputChange,
        label = { Text(labelText) },
        modifier = modifier.fillMaxWidth(),
        enabled = enabled,
        isError = isError,
        singleLine = true,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
    )
}
