package ir.training.currency.data.currency.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.training.currency.data.ServiceRepository
import ir.training.currency.data.currency.rate.dataprovider.CurrencyRateDataProvider
import ir.training.currency.data.currency.rate.dataprovider.CurrencyRateDataProviderImpl
import ir.training.currency.data.currency.rate.repository.CurrencyRateRepositoryImpl
import ir.training.currency.domain.repository.currency.item.rate.CurrencyRateRepository

@Module
@InstallIn(SingletonComponent::class)
internal class CurrencyRateDIModule {

    @Provides
    fun provideItemDataProvider(repo: ServiceRepository): CurrencyRateDataProvider {
        return CurrencyRateDataProviderImpl(repo)
    }

    @Provides
    fun provideItemRepository(dataProvider: CurrencyRateDataProvider): CurrencyRateRepository {
        return CurrencyRateRepositoryImpl(dataProvider)
    }


}