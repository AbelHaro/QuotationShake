package dadm.ahararm.quotationshake.di

import dadm.ahararm.quotationshake.data.favourites.FavouritesDataSource
import dadm.ahararm.quotationshake.data.favourites.FavouritesDataSourceImpl
import dadm.ahararm.quotationshake.data.favourites.FavouritesRepository
import dadm.ahararm.quotationshake.data.favourites.FavouritesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class FavouritesBinderModule {

    @Binds
    abstract fun bindFavouritesDataSource(favouritesDataSourceImpl: FavouritesDataSourceImpl): FavouritesDataSource

    @Binds
    abstract fun bindFavouritesRepository(favouritesRepositoryImpl: FavouritesRepositoryImpl): FavouritesRepository

}