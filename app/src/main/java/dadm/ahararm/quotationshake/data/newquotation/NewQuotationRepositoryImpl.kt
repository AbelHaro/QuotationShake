package dadm.ahararm.quotationshake.data.newquotation

import dadm.ahararm.quotationshake.domain.model.Quotation
import javax.inject.Inject

class NewQuotationRepositoryImpl @Inject constructor(
    private val newQuotationDataSource: NewQuotationDataSource
) : NewQuotationRepository {

    override suspend fun getNewQuotation(): Result<Quotation> {
        return newQuotationDataSource.getQuotation()
    }
}
