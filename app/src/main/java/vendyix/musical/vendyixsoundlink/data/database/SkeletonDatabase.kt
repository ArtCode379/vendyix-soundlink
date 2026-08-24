package vendyix.musical.vendyixsoundlink.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import vendyix.musical.vendyixsoundlink.data.dao.CartItemDao
import vendyix.musical.vendyixsoundlink.data.dao.OrderDao
import vendyix.musical.vendyixsoundlink.data.database.converter.Converters
import vendyix.musical.vendyixsoundlink.data.entity.CartItemEntity
import vendyix.musical.vendyixsoundlink.data.entity.OrderEntity

@Database(
    entities = [CartItemEntity::class, OrderEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class UDLXJDatabase : RoomDatabase() {

    abstract fun cartItemDao(): CartItemDao

    abstract fun orderDao(): OrderDao
}