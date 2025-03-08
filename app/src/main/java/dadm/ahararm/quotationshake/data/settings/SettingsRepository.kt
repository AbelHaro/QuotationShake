package dadm.ahararm.quotationshake.data.settings

import kotlinx.coroutines.flow.Flow

interface SettingsRepository {

    fun getUserName(): Flow<String>

    suspend fun getUserNameSnapshot(): String

    suspend fun setUserName(userName: String)
}