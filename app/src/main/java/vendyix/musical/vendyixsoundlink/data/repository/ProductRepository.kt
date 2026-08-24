package vendyix.musical.vendyixsoundlink.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import vendyix.musical.vendyixsoundlink.data.model.Product
import vendyix.musical.vendyixsoundlink.data.model.ProductCategory

class ProductRepository {
    private val products = listOf(
        product(
            1,
            "Aurora ST Electric Guitar",
            "Versatile double-cut electric guitar with an alder body, smooth maple neck, and three articulate pickups.",
            ProductCategory.GUITARS,
            429.00,
            "photo-1564186763535-ebb21ef5277f",
        ),
        product(
            2,
            "Heritage Dreadnought Acoustic",
            "Warm steel-string acoustic with a solid spruce top and clear projection for songs and performances.",
            ProductCategory.GUITARS,
            349.00,
            "photo-1510915361894-db8b60106cb1",
        ),
        product(
            3,
            "Nova 88 Stage Piano",
            "Expressive 88-key stage piano with weighted action, concert samples, and simple performance controls.",
            ProductCategory.KEYBOARDS,
            899.00,
            "photo-1520523839897-bd0b52f945a0",
        ),
        product(
            4,
            "Pulse Mini Synth",
            "Compact synthesizer with hands-on controls, analogue-style voices, arpeggiator, and USB connectivity.",
            ProductCategory.KEYBOARDS,
            279.00,
            "photo-1598488035139-bdbb2231ce04",
        ),
        product(
            5,
            "Foundry 5-Piece Drum Kit",
            "Responsive five-piece shell pack with punchy low end, controlled overtones, and durable wrap finish.",
            ProductCategory.DRUMS,
            749.00,
            "photo-1519892300165-cb5542fb47c7",
        ),
        product(
            6,
            "Studio One Condenser Mic",
            "Large-diaphragm cardioid microphone that captures vocals and acoustic instruments with clarity.",
            ProductCategory.MICROPHONES,
            189.00,
            "photo-1590602847861-f357a9332bbc",
        ),
        product(
            7,
            "LiveCore Dynamic Mic",
            "Road-ready vocal microphone with focused midrange, excellent rejection, clip, and protective pouch.",
            ProductCategory.MICROPHONES,
            89.00,
            "photo-1524678606370-a47ad25cb82a",
        ),
        product(
            8,
            "Reference 45 Headphones",
            "Closed-back studio headphones with accurate bass, detailed mids, and soft replaceable ear pads.",
            ProductCategory.HEADPHONES,
            149.00,
            "photo-1505740420928-5e560c06d30e",
        ),
        product(
            9,
            "Airwave Wireless Headphones",
            "Comfortable wireless headphones with balanced tuning, active noise control, and 35-hour listening.",
            ProductCategory.HEADPHONES,
            219.00,
            "photo-1484704849700-f032a568e944",
        ),
        product(
            10,
            "Ember 30 Guitar Combo",
            "Compact 30-watt guitar amplifier with clean and drive channels, three-band EQ, and reverb.",
            ProductCategory.AMPLIFIERS,
            259.00,
            "photo-1524650359799-842906ca1c06",
        ),
        product(
            11,
            "Monitor 7 Active Pair",
            "Matched active studio monitors with a wide sweet spot, honest bass, and room tuning controls.",
            ProductCategory.SPEAKERS,
            499.00,
            "photo-1545454675-3531b543be5d",
        ),
        product(
            12,
            "StageLine 12 PA Speaker",
            "Rugged powered PA speaker with clear vocal projection, flexible inputs, and useful onboard DSP.",
            ProductCategory.SPEAKERS,
            579.00,
            "photo-1558537348-c0f8e733989d",
        ),
    )

    fun observeById(id: Int): Flow<Product?> = flowOf(products.find { it.id == id })

    fun getById(id: Int): Product? = products.find { it.id == id }

    fun observeAll(): Flow<List<Product>> = flowOf(products)

    private fun product(
        id: Int,
        title: String,
        description: String,
        category: ProductCategory,
        price: Double,
        imageId: String,
    ) = Product(
        id = id,
        title = title,
        description = description,
        category = category,
        price = price,
        imageUrl = "https://images.unsplash.com/$imageId?w=1200",
    )
}
