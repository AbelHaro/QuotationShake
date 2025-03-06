package dadm.ahararm.quotationshake.data.newquotation

import dadm.ahararm.quotationshake.domain.model.Quotation
import javax.inject.Inject

class NewQuotationDataSourceImpl @Inject constructor() : NewQuotationDataSource {
    override suspend fun getQuotation(): Result<Quotation> {
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