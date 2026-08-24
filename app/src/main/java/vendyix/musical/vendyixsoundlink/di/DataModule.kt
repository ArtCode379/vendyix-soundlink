package vendyix.musical.vendyixsoundlink.di

import vendyix.musical.vendyixsoundlink.data.repository.CartRepository
import vendyix.musical.vendyixsoundlink.data.repository.UDLXJOnboardingRepo
import vendyix.musical.vendyixsoundlink.data.repository.OrderRepository
import vendyix.musical.vendyixsoundlink.data.repository.ProductRepository

import org.koin.core.qualifier.named
import org.koin.dsl.module

val dataModule = module {
    includes(databaseModule, dataStoreModule)

    single {
        UDLXJOnboardingRepo(
            udlxjOnboardingStoreManager = get(),
            coroutineDispatcher = get(named("IO"))
        )
    }

    single { ProductRepository() }

    single {
        CartRepository(
            cartItemDao = get(),
            coroutineDispatcher = get(named("IO"))
        )
    }

    single {
        OrderRepository(
            orderDao = get(),
            coroutineDispatcher = get(named("IO"))
        )
    }
}