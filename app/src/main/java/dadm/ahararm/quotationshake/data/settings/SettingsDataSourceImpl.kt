package dadm.ahararm.quotationshake.data.settings

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class SettingsDataSourceImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : SettingsDataSource {

    companion object {
        private val USER_NAME = stringPreferencesKey("username")
        private val LANGUAGE = stringPreferencesKey("language")
    }

    private fun get(key: Preferences.Key<String>): Flow<String> {
        return dataStore.data.catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else throw exception
        }.map { preferences ->
            preferences[key] ?: when (key) {
                LANGUAGE -> "en"
                else -> ""
            }
        }
    }

    private suspend fun getSnapshot(key: Preferences.Key<String>): String =
        dataStore.data.first()[key] ?: when (key) {
            LANGUAGE -> "en"
            else -> ""
        }

    private suspend fun set(key: Preferences.Key<String>, value: String) {
        dataStore.edit { preferences ->
            preferences[key] = value
        }
    }

    override fun getUserName(): Flow<String> = get(USER_NAME)
    override suspend fun getUserNameSnapshot(): String = getSnapshot(USER_NAME)
    override suspend fun setUserName(userName: String) = set(USER_NAME, userName)

    override fun getLanguage(): Flow<String> = get(LANGUAGE)
    override suspend fun getLanguageSnapshot(): String = getSnapshot(LANGUAGE)
    override suspend fun setLanguage(language: String) = set(LANGUAGE, language)
}