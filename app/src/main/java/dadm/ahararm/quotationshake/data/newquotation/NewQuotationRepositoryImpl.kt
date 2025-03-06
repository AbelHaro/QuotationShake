package dadm.ahararm.quotationshake.data.newquotation

import dadm.ahararm.quotationshake.domain.model.Quotation
import dadm.ahararm.quotationshake.utils.NoInternetException
import javax.inject.Inject

class NewQuotationRepositoryImpl @Inject constructor(
    private val newQuotationDataSource: NewQuotationDataSource,
    private val connectivityChecker: ConnectivityChecker
) : NewQuotationRepository {

    override suspend fun getNewQuotation(): Result<Quotation> {
        if (!connectivityChecker.isConnectionAvailable()) {
            return Result.failure(NoInternetException())
        }
        return newQuotationDataSource.getQuotation()
    }
}
