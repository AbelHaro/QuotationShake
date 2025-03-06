package dadm.ahararm.quotationshake.data.newquotation

import dadm.ahararm.quotationshake.data.newquotation.model.RemoteQuotationDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewQuotationRetrofit {

    @GET("api/1.0/")
    suspend fun getQuotation(
        @Query("method") method: String = "getQuote",
        @Query("format") format: String = "json",
        @Query("lang") lang: String
    ): Response<RemoteQuotationDto>
}
