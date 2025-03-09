package dadm.ahararm.quotationshake.data.settings

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SettingsRepositoryImpl @Inject constructor(private val settingsDataSource: SettingsDataSource) :
    SettingsRepository {
    override fun getUserName(): Flow<String> = settingsDataSource.getUserName()
    override suspend fun getUserNameSnapshot(): String = settingsDataSource.getUserNameSnapshot()
    override suspend fun setUserName(userName: String) = settingsDataSource.setUserName(userName)

    override fun getLanguage(): Flow<String> = settingsDataSource.getLanguage()
    override suspend fun getLanguageSnapshot(): String = settingsDataSource.getLanguageSnapshot()
    override suspend fun setLanguage(language: String) = settingsDataSource.setLanguage(language)
}