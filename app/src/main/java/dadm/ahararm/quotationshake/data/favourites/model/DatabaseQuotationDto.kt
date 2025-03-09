package dadm.ahararm.quotationshake.data.favourites.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import dadm.ahararm.quotationshake.data.favourites.FavouritesContract.FavouritesTable.COLUMN_AUTHOR
import dadm.ahararm.quotationshake.data.favourites.FavouritesContract.FavouritesTable.COLUMN_ID
import dadm.ahararm.quotationshake.data.favourites.FavouritesContract.FavouritesTable.COLUMN_TEXT
import dadm.ahararm.quotationshake.data.favourites.FavouritesContract.FavouritesTable.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class DatabaseQuotationDto(
    @PrimaryKey
    @ColumnInfo(name = COLUMN_ID)
    val id: String,

    @ColumnInfo(name = COLUMN_TEXT)
    val text: String,

    @ColumnInfo(name = COLUMN_AUTHOR)
    val author: String
)
