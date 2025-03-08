package dadm.ahararm.quotationshake.ui.settings

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import dadm.ahararm.quotationshake.R
import dadm.ahararm.quotationshake.data.settings.SettingsPreferenceDataStore
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SettingsFragment : PreferenceFragmentCompat() {

    @Inject
    lateinit var settingsPreferenceDataStore: SettingsPreferenceDataStore

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        preferenceManager.preferenceDataStore = settingsPreferenceDataStore
        setPreferencesFromResource(R.xml.preferences_settings, rootKey)
    }
}
