package vendyix.musical.vendyixsoundlink.di

import vendyix.musical.vendyixsoundlink.ui.viewmodel.AppViewModel
import vendyix.musical.vendyixsoundlink.ui.viewmodel.CartViewModel
import vendyix.musical.vendyixsoundlink.ui.viewmodel.CheckoutViewModel
import vendyix.musical.vendyixsoundlink.ui.viewmodel.UDLXJOnboardingVM
import vendyix.musical.vendyixsoundlink.ui.viewmodel.OrderViewModel
import vendyix.musical.vendyixsoundlink.ui.viewmodel.ProductDetailsViewModel
import vendyix.musical.vendyixsoundlink.ui.viewmodel.ProductViewModel
import vendyix.musical.vendyixsoundlink.ui.viewmodel.UDLXJSplashVM
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModule = module {
    viewModel {
        AppViewModel(
            cartRepository = get()
        )
    }

    viewModel {
        UDLXJSplashVM(
            onboardingRepository = get()
        )
    }

    viewModel {
        UDLXJOnboardingVM(
            onboardingRepository = get()
        )
    }

    viewModel {
        ProductViewModel(
            productRepository = get(),
            cartRepository = get(),
        )
    }

    viewModel {
        ProductDetailsViewModel(
            productRepository = get(),
            cartRepository = get(),
        )
    }

    viewModel {
        CheckoutViewModel(
            cartRepository = get(),
            productRepository = get(),
            orderRepository = get(),
        )
    }

    viewModel {
        CartViewModel(
            cartRepository = get(),
            productRepository = get(),
        )
    }

    viewModel {
        OrderViewModel(
            orderRepository = get(),
        )
    }
}