package vendyix.musical.vendyixsoundlink.di

import androidx.room.Room
import vendyix.musical.vendyixsoundlink.data.database.UDLXJDatabase
import org.koin.dsl.module

private const val DB_NAME = "udlxj_db"

val databaseModule = module {
    single {
        Room.databaseBuilder(
            context = get(),
            klass = UDLXJDatabase::class.java,
            name = DB_NAME
        ).build()
    }

    single { get<UDLXJDatabase>().cartItemDao() }

    single { get<UDLXJDatabase>().orderDao() }
}