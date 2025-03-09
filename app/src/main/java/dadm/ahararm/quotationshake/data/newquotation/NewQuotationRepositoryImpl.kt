package dadm.ahararm.quotationshake.data.newquotation

import dadm.ahararm.quotationshake.data.newquotation.model.toDomain
import dadm.ahararm.quotationshake.data.settings.SettingsRepository
import dadm.ahararm.quotationshake.domain.model.Quotation
import dadm.ahararm.quotationshake.utils.NoInternetException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewQuotationRepositoryImpl @Inject constructor(
    private val newQuotationDataSource: NewQuotationDataSource,
    private val connectivityChecker: ConnectivityChecker,
    private val settingsRepository: SettingsRepository
) : NewQuotationRepository {

    private lateinit var language: String

    init {
        CoroutineScope(SupervisorJob()).launch {
            settingsRepository.getLanguage().collect { languageCode ->
                language = languageCode.ifEmpty { "en" }
            }
        }
    }

    override suspend fun getNewQuotation(): Result<Quotation> {

        if (!connectivityChecker.isConnectionAvailable()) {
            return Result.failure(NoInternetException())
        }
        return newQuotationDataSource.getQuotation(language).toDomain()
    }
}
