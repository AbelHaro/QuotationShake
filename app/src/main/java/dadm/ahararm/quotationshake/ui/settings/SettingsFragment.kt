package dadm.ahararm.quotationshake.ui.settings

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import dadm.ahararm.quotationshake.R

class SettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences_settings, rootKey)
    }
}
