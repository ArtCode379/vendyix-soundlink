package vendyix.musical.vendyixsoundlink.ui.composable.screen.order

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel
import vendyix.musical.vendyixsoundlink.R
import vendyix.musical.vendyixsoundlink.data.entity.OrderEntity
import vendyix.musical.vendyixsoundlink.ui.state.DataUiState
import vendyix.musical.vendyixsoundlink.ui.theme.SuccessGreen
import vendyix.musical.vendyixsoundlink.ui.viewmodel.OrderViewModel
import java.time.format.DateTimeFormatter

@Composable
fun OrdersScreen(modifier: Modifier = Modifier, viewModel: OrderViewModel = koinViewModel()) {
    val state by viewModel.ordersState.collectAsState()
    val orders = (state as? DataUiState.Populated)?.data.orEmpty().sortedByDescending { it.timestamp }
    if (orders.isEmpty()) {
        Column(modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            Text(stringResource(R.string.udlxj_orders_state_empty_primary_text), style = MaterialTheme.typography.titleLarge)
            Text(stringResource(R.string.udlxj_orders_empty_hint), color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    } else {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            contentPadding = androidx.compose.foundation.layout.PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            items(orders, key = { it.orderNumber }) { OrderCard(it) }
        }
    }
}

@Composable
private fun OrderCard(order: OrderEntity) {
    Card(shape = RoundedCornerShape(16.dp)) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(stringResource(R.string.udlxj_order_number, order.orderNumber), fontWeight = FontWeight.Bold)
                Text(
                    stringResource(R.string.udlxj_order_reserved),
                    color = androidx.compose.ui.graphics.Color.White,
                    modifier = Modifier
                        .background(SuccessGreen, RoundedCornerShape(50))
                        .padding(horizontal = 10.dp, vertical = 4.dp),
                )
            }
            Text(order.timestamp.format(DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm")), color = MaterialTheme.colorScheme.onSurfaceVariant)
            Text(order.description)
            Text(stringResource(R.string.udlxj_price, order.price), color = MaterialTheme.colorScheme.primary, style = MaterialTheme.typography.titleMedium)
            Text(stringResource(R.string.udlxj_pickup_24_hours), color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}
