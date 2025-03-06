package dadm.ahararm.quotationshake.data.newquotation.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RemoteQuotationDto(
    @Json(name = "quoteText") val quoteText: String,
    @Json(name = "quoteAuthor") val quoteAuthor: String,
    @Json(name = "senderName") val senderName: String,
    @Json(name = "senderLink") val senderLink: String,
    @Json(name = "quoteLink") val quoteLink: String
)
