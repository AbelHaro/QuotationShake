package dadm.ahararm.quotationshake.data.favourites

import dadm.ahararm.quotationshake.data.favourites.model.DatabaseQuotationDto
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavouritesDataSourceImpl @Inject constructor(
    private val favouritesDao: FavouritesDao
) : FavouritesDataSource {

    override suspend fun addQuotationToFavourites(databaseQuotationDto: DatabaseQuotationDto) {
        favouritesDao.addQuotationToFavourites(databaseQuotationDto)
    }

    override suspend fun removeQuotationFromFavourites(databaseQuotationDto: DatabaseQuotationDto) {
        favouritesDao.removeQuotationFromFavourites(databaseQuotationDto)
    }

    override fun getAllFavouritesQuotations(): Flow<List<DatabaseQuotationDto>> {
        return favouritesDao.getAllFavouritesQuotations()
    }

    override fun getQuotationById(id: String): Flow<DatabaseQuotationDto> {
        return favouritesDao.getQuotationById(id)
    }

    override suspend fun deleteAllFavourites() {
        favouritesDao.deleteAllFavourites()
    }
}