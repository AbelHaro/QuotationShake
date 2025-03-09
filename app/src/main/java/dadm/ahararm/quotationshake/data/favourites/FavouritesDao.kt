package dadm.ahararm.quotationshake.data.favourites

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dadm.ahararm.quotationshake.data.favourites.model.DatabaseQuotationDto
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouritesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addQuotationToFavourites(databaseQuotationDto: DatabaseQuotationDto)

    @Delete
    suspend fun removeQuotationFromFavourites(databaseQuotationDto: DatabaseQuotationDto)

    @Query("SELECT * FROM ${FavouritesContract.FavouritesTable.TABLE_NAME}")
    fun getAllFavouritesQuotations(): Flow<List<DatabaseQuotationDto>>

    @Query("SELECT * FROM ${FavouritesContract.FavouritesTable.TABLE_NAME} WHERE ${FavouritesContract.FavouritesTable.COLUMN_ID} = :id")
    fun getQuotationById(id: String): Flow<DatabaseQuotationDto?>

    @Query("DELETE FROM ${FavouritesContract.FavouritesTable.TABLE_NAME}")
    suspend fun deleteAllFavourites()
}