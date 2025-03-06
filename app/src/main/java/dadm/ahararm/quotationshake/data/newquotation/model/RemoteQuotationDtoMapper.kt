package dadm.ahararm.quotationshake.data.newquotation.model

import dadm.ahararm.quotationshake.domain.model.Quotation
import retrofit2.Response
import java.io.IOException

// Convierte un RemoteQuotationDto en un objeto de dominio Quotation
fun RemoteQuotationDto.toDomain() = Quotation(
    id = quoteLink,
    text = quoteText,
    author = quoteAuthor
)

// Convierte una respuesta de Retrofit en un Result<Quotation>
fun Response<RemoteQuotationDto>.toDomain() =
    if (isSuccessful) Result.success((body() as RemoteQuotationDto).toDomain())
    else Result.failure(IOException())
