package dadm.ahararm.quotationshake.data.favourites

import dadm.ahararm.quotationshake.domain.model.Quotation
import kotlinx.coroutines.flow.Flow

interface FavouritesRepository {

    suspend fun addQuotationToFavourites(quotation: Quotation)

    suspend fun removeQuotationFromFavourites(quotation: Quotation)

    fun getAllFavouritesQuotations(): Flow<List<Quotation>>

    fun getQuotationById(id: String): Flow<Quotation?>

    suspend fun deleteAllFavourites()
}