package dadm.ahararm.quotationshake.data.newquotation

import dadm.ahararm.quotationshake.domain.model.Quotation

interface NewQuotationRepository {

    suspend fun getNewQuotation(): Result<Quotation>
}