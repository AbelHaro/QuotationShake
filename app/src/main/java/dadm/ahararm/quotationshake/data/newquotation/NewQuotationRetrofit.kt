package dadm.ahararm.quotationshake.data.newquotation

import dadm.ahararm.quotationshake.data.newquotation.model.RemoteQuotationDto
import retrofit2.Response
import retrofit2.http.GET

interface NewQuotationRetrofit {
    @GET("api/1.0/?method=getQuote&format=json&lang=en")
    suspend fun getQuotation(): Response<RemoteQuotationDto>
}
