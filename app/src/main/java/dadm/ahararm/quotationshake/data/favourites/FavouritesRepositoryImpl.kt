package dadm.ahararm.quotationshake.data.favourites

import dadm.ahararm.quotationshake.data.favourites.model.toDatabaseDto
import dadm.ahararm.quotationshake.data.favourites.model.toDomain
import dadm.ahararm.quotationshake.domain.model.Quotation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavouritesRepositoryImpl @Inject constructor(
    private val favouritesDataSource: FavouritesDataSource
) : FavouritesRepository {

    override suspend fun addQuotationToFavourites(quotation: Quotation) {
        favouritesDataSource.addQuotationToFavourites(quotation.toDatabaseDto())
    }

    override suspend fun removeQuotationFromFavourites(quotation: Quotation) {
        favouritesDataSource.removeQuotationFromFavourites(quotation.toDatabaseDto())
    }

    override fun getAllFavouritesQuotations(): Flow<List<Quotation>> {
        return favouritesDataSource.getAllFavouritesQuotations()
            .map { databaseQuotations ->
                databaseQuotations.map { it.toDomain() }
            }
    }

    override fun getQuotationById(id: String): Flow<Quotation?> {
        return favouritesDataSource.getQuotationById(id)
            .map { it?.toDomain() }
    }

    override suspend fun deleteAllFavourites() {
        favouritesDataSource.deleteAllFavourites()
    }
}