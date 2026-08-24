package vendyix.musical.vendyixsoundlink.di

import vendyix.musical.vendyixsoundlink.data.datastore.UDLXJOnboardingPrefs
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataStoreModule = module {
    single { UDLXJOnboardingPrefs(androidContext()) }
}