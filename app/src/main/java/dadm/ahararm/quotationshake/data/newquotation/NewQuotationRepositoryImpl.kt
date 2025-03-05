package dadm.ahararm.quotationshake.data.newquotation

import dadm.ahararm.quotationshake.domain.model.Quotation
import javax.inject.Inject

class NewQuotationRepositoryImpl @Inject constructor() : NewQuotationRepository {

    override suspend fun getNewQuotation(): Result<Quotation> {
        val num = (0..99).random()

        if (num >= 90) {
            return Result.failure(Exception("Error getting new quotation"))
        }

        return Result.success(
            Quotation(
                id = num.toString(),
                text = "Quotation text #$num",
                author = "Author #$num"
            )
        )
    }
}
