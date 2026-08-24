package vendyix.musical.vendyixsoundlink.ui.composable.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel
import vendyix.musical.vendyixsoundlink.R
import vendyix.musical.vendyixsoundlink.data.model.Product
import vendyix.musical.vendyixsoundlink.data.model.ProductCategory
import vendyix.musical.vendyixsoundlink.ui.state.DataUiState
import vendyix.musical.vendyixsoundlink.ui.viewmodel.ProductViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: ProductViewModel = koinViewModel(),
    onNavigateToProductDetails: (productId: Int) -> Unit,
) {
    val productsState by viewModel.productsState.collectAsState()
    val products = (productsState as? DataUiState.Populated)?.data.orEmpty()
    var category by remember { mutableStateOf<ProductCategory?>(null) }
    val filtered = category?.let { selected -> products.filter { it.category == selected } } ?: products

    Column(modifier = modifier.fillMaxSize()) {
        if (products.isEmpty()) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(stringResource(R.string.udlxj_products_state_empty_primary_text))
            }
        } else {
            FeaturedCarousel(products.take(4), onNavigateToProductDetails)
            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                item {
                    AssistChip(
                        onClick = { category = null },
                        label = { Text(stringResource(R.string.udlxj_category_all)) },
                        colors = categoryChipColors(category == null),
                    )
                }
                items(ProductCategory.entries.size) { index ->
                    val item = ProductCategory.entries[index]
                    AssistChip(
                        onClick = { category = item },
                        label = { Text(stringResource(item.titleRes)) },
                        colors = categoryChipColors(category == item),
                    )
                }
            }
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 24.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                items(filtered, key = { it.id }) { product ->
                    ProductCard(product, onNavigateToProductDetails)
                }
            }
        }
    }
}

@Composable
private fun categoryChipColors(selected: Boolean) = AssistChipDefaults.assistChipColors(
    containerColor = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant,
    labelColor = if (selected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface,
)

@Composable
private fun FeaturedCarousel(products: List<Product>, onClick: (Int) -> Unit) {
    val pager = rememberPagerState(pageCount = { products.size })
    LaunchedEffect(products.size) {
        while (products.size > 1) {
            delay(4000)
            pager.animateScrollToPage((pager.currentPage + 1) % products.size)
        }
    }
    Column {
        HorizontalPager(
            state = pager,
            contentPadding = PaddingValues(horizontal = 16.dp),
            pageSpacing = 12.dp,
        ) { index ->
            val product = products[index]
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(196.dp)
                    .clip(RoundedCornerShape(18.dp))
                    .clickable { onClick(product.id) },
            ) {
                AsyncImage(
                    model = product.imageUrl,
                    contentDescription = product.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize(),
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(18.dp),
                    contentAlignment = Alignment.BottomStart,
                ) {
                    Column {
                        Text(product.title, color = Color.White, style = MaterialTheme.typography.titleLarge)
                        Text(stringResource(R.string.udlxj_price, product.price), color = Color.White, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.Center,
        ) {
            products.indices.forEach { index ->
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    tint = if (index == pager.currentPage) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline,
                    modifier = Modifier.size(8.dp),
                )
            }
        }
    }
}

@Composable
private fun ProductCard(product: Product, onClick: (Int) -> Unit) {
    Card(
        modifier = Modifier.clickable { onClick(product.id) },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
    ) {
        AsyncImage(
            model = product.imageUrl,
            contentDescription = product.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(130.dp),
        )
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(5.dp),
        ) {
            Text(product.title, maxLines = 2, overflow = TextOverflow.Ellipsis, style = MaterialTheme.typography.titleMedium)
            Text(stringResource(product.category.titleRes), color = MaterialTheme.colorScheme.onSurfaceVariant, style = MaterialTheme.typography.bodyMedium)
            Text(stringResource(R.string.udlxj_price, product.price), color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
        }
    }
}
