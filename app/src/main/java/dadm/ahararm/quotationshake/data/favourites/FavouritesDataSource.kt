package dadm.ahararm.quotationshake.data.favourites

import dadm.ahararm.quotationshake.data.favourites.model.DatabaseQuotationDto
import kotlinx.coroutines.flow.Flow

interface FavouritesDataSource {

    suspend fun addQuotationToFavourites(databaseQuotationDto: DatabaseQuotationDto)

    suspend fun removeQuotationFromFavourites(databaseQuotationDto: DatabaseQuotationDto)

    fun getAllFavouritesQuotations(): Flow<List<DatabaseQuotationDto>>

    fun getQuotationById(id: String): Flow<DatabaseQuotationDto?>

    suspend fun deleteAllFavourites()
}