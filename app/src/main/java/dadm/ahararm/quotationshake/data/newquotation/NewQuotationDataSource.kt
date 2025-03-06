package dadm.ahararm.quotationshake.data.newquotation

import dadm.ahararm.quotationshake.domain.model.Quotation

interface NewQuotationDataSource {

    suspend fun getQuotation(): Result<Quotation>
}