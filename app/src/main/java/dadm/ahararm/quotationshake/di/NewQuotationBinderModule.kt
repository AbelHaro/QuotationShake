package dadm.ahararm.quotationshake.di

import dadm.ahararm.quotationshake.data.newquotation.NewQuotationDataSource
import dadm.ahararm.quotationshake.data.newquotation.NewQuotationDataSourceImpl
import dadm.ahararm.quotationshake.data.newquotation.NewQuotationRepository
import dadm.ahararm.quotationshake.data.newquotation.NewQuotationRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class NewQuotationBinderModule {

    @Binds
    abstract fun bindNewQuotationRepository(newQuotationRepositoryImpl: NewQuotationRepositoryImpl): NewQuotationRepository

    @Binds
    abstract fun bindNewQuotationDataSource(newQuotationDataSourceImpl: NewQuotationDataSourceImpl): NewQuotationDataSource
}