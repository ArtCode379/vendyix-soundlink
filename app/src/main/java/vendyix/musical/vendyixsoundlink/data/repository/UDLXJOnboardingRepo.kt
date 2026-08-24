package vendyix.musical.vendyixsoundlink.data.repository

import vendyix.musical.vendyixsoundlink.data.datastore.UDLXJOnboardingPrefs
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class UDLXJOnboardingRepo(
    private val udlxjOnboardingStoreManager: UDLXJOnboardingPrefs,
    private val coroutineDispatcher: CoroutineDispatcher,
) {

    fun observeOnboardingState(): Flow<Boolean?> {
        return udlxjOnboardingStoreManager.onboardedStateFlow
    }

    suspend fun setOnboardingState(state: Boolean) {
        withContext(coroutineDispatcher) {
            udlxjOnboardingStoreManager.setOnboardedState(state)
        }
    }
}