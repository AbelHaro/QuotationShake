package dadm.ahararm.quotationshake.data.settings

import androidx.preference.PreferenceDataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class SettingsPreferenceDataStore @Inject constructor(
    private val settingsRepository: SettingsRepository
) : PreferenceDataStore() {

    override fun putString(key: String?, value: String?) {
        CoroutineScope(Dispatchers.IO).launch {
            when (key) {
                "username" -> settingsRepository.setUserName(value ?: "")
            }
        }
    }

    override fun getString(key: String?, defValue: String?): String? {
        return runBlocking(Dispatchers.IO) {
            when (key) {
                "username" -> settingsRepository.getUserNameSnapshot()
                else -> defValue
            }
        }
    }
}