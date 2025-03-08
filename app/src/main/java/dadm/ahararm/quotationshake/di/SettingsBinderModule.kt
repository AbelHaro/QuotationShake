package dadm.ahararm.quotationshake.di

import dadm.ahararm.quotationshake.data.settings.SettingsDataSource
import dadm.ahararm.quotationshake.data.settings.SettingsDataSourceImpl
import dadm.ahararm.quotationshake.data.settings.SettingsRepository
import dadm.ahararm.quotationshake.data.settings.SettingsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class SettingsBinderModule {

    @Binds
    abstract fun bindSettingsDataSource(settingsDataSourceImpl: SettingsDataSourceImpl): SettingsDataSource

    @Binds
    abstract fun bindSettingsRepository(settingsRepositoryImpl: SettingsRepositoryImpl): SettingsRepository
}