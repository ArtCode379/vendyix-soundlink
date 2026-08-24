package vendyix.musical.vendyixsoundlink.data.model

import androidx.annotation.StringRes
import vendyix.musical.vendyixsoundlink.R

enum class ProductCategory(@field:StringRes val titleRes: Int) {
    GUITARS(R.string.udlxj_category_guitars),
    KEYBOARDS(R.string.udlxj_category_keyboards),
    DRUMS(R.string.udlxj_category_drums),
    MICROPHONES(R.string.udlxj_category_microphones),
    HEADPHONES(R.string.udlxj_category_headphones),
    AMPLIFIERS(R.string.udlxj_category_amplifiers),
    SPEAKERS(R.string.udlxj_category_speakers),
}
