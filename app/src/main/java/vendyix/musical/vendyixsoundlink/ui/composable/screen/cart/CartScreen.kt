package vendyix.musical.vendyixsoundlink.ui.composable.screen.cart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import org.koin.compose.viewmodel.koinViewModel
import vendyix.musical.vendyixsoundlink.R
import vendyix.musical.vendyixsoundlink.ui.state.CartItemUiState
import vendyix.musical.vendyixsoundlink.ui.state.DataUiState
import vendyix.musical.vendyixsoundlink.ui.viewmodel.CartViewModel

@Composable
fun CartScreen(
    modifier: Modifier = Modifier,
    viewModel: CartViewModel = koinViewModel(),
    onNavigateToCheckoutScreen: () -> Unit,
) {
    val state by viewModel.cartItemsState.collectAsStateWithLifecycle()
    val total by viewModel.totalPrice.collectAsStateWithLifecycle()
    val items = (state as? DataUiState.Populated)?.data.orEmpty()

    if (items.isEmpty()) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(stringResource(R.string.udlxj_cart_state_empty_primary_text), style = MaterialTheme.typography.titleLarge)
            Text(stringResource(R.string.udlxj_start_shopping), color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    } else {
        Column(modifier.fillMaxSize()) {
            LazyColumn(
                modifier = Modifier.weight(1f),
                contentPadding = androidx.compose.foundation.layout.PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                items(items, key = { it.productId }) { item ->
                    CartItem(
                        item = item,
                        onPlus = { viewModel.incrementProductInCart(item.productId) },
                        onMinus = {
                            if (item.quantity == 1) {
                                viewModel.deleteFromCart(item.productId)
                            } else {
                                viewModel.decrementItemInCart(item.productId)
                            }
                        },
                        onDelete = { viewModel.deleteFromCart(item.productId) },
                    )
                }
            }
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(stringResource(R.string.udlxj_total), style = MaterialTheme.typography.titleLarge)
                    Text(stringResource(R.string.udlxj_price, total), color = MaterialTheme.colorScheme.primary, style = MaterialTheme.typography.titleLarge)
                }
                Button(onClick = onNavigateToCheckoutScreen, modifier = Modifier.fillMaxWidth()) {
                    Text(stringResource(R.string.udlxj_proceed_checkout))
                }
            }
        }
    }
}

@Composable
private fun CartItem(item: CartItemUiState, onPlus: () -> Unit, onMinus: () -> Unit, onDelete: () -> Unit) {
    Card(shape = RoundedCornerShape(16.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            AsyncImage(
                model = item.productImageUrl,
                contentDescription = item.productTitle,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(60.dp),
            )
            Column(Modifier.weight(1f)) {
                Text(item.productTitle, fontWeight = FontWeight.SemiBold)
                Text(stringResource(R.string.udlxj_price, item.productPrice), color = MaterialTheme.colorScheme.primary)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    OutlinedButton(onClick = onMinus) { Text("−") }
                    Text(item.quantity.toString(), modifier = Modifier.padding(horizontal = 12.dp))
                    OutlinedButton(onClick = onPlus) { Text("+") }
                }
            }
            IconButton(onClick = onDelete) {
                Icon(Icons.Default.Delete, stringResource(R.string.udlxj_delete_item_icon_description))
            }
        }
    }
}
