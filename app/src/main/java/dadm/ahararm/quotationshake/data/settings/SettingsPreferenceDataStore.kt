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

    companion object {
        private const val KEY_USERNAME = "username"
        private const val KEY_LANGUAGE = "language"
    }

    override fun putString(key: String?, value: String?) {
        CoroutineScope(Dispatchers.IO).launch {
            when (key) {
                KEY_USERNAME -> settingsRepository.setUserName(value ?: "")
                KEY_LANGUAGE -> settingsRepository.setLanguage(value ?: "")
            }
        }
    }

    override fun getString(key: String?, defValue: String?): String? {
        return runBlocking(Dispatchers.IO) {
            when (key) {
                KEY_USERNAME -> settingsRepository.getUserNameSnapshot()
                KEY_LANGUAGE -> settingsRepository.getLanguageSnapshot()
                else -> defValue
            }
        }
    }
}