package dadm.ahararm.quotationshake.data.newquotation

import dadm.ahararm.quotationshake.data.newquotation.model.RemoteQuotationDto
import retrofit2.Response

interface NewQuotationDataSource {

    suspend fun getQuotation(lang: String): Response<RemoteQuotationDto>
}