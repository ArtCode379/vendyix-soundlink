package vendyix.musical.vendyixsoundlink.data.model

data class Product(
    val id: Int,
    val title: String,
    val description: String,
    val category: ProductCategory,
    val price: Double,
    val imageUrl: String,
)