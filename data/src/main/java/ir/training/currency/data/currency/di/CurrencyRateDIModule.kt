package ir.training.currency.data.currency.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.training.currency.data.ServiceRepository
import ir.training.currency.data.currency.exchange.dataprovider.CurrencyExchangeDataProvider
import ir.training.currency.data.currency.exchange.dataprovider.CurrencyExchangeDataProviderImpl
import ir.training.currency.data.currency.exchange.repository.CurrencyExchangeRepositoryImpl
import ir.training.currency.data.currency.log.dataprovider.CurrencyLogDataProvider
import ir.training.currency.data.currency.log.dataprovider.CurrencyLogDataProviderImpl
import ir.training.currency.data.currency.log.repository.CurrencyLogRepositoryImpl
import ir.training.currency.data.currency.rate.dataprovider.CurrencyRateDataProvider
import ir.training.currency.data.currency.rate.dataprovider.CurrencyRateDataProviderImpl
import ir.training.currency.data.currency.rate.repository.CurrencyRateRepositoryImpl
import ir.training.currency.domain.repository.currency.item.exchange.CurrencyExchangeRepository
import ir.training.currency.domain.repository.currency.item.rate.CurrencyRateRepository
import ir.training.currency.domain.repository.currency.log.CurrencyLogRepository

@Module
@InstallIn(SingletonComponent::class)
internal class CurrencyRateDIModule {

    @Provides
    fun provideCurrencyRateDataProvider(repo: ServiceRepository): CurrencyRateDataProvider {
        return CurrencyRateDataProviderImpl(repo)
    }

    @Provides
    fun provideCurrencyRateRepository(dataProvider: CurrencyRateDataProvider): CurrencyRateRepository {
        return CurrencyRateRepositoryImpl(dataProvider)
    }

    @Provides
    fun provideCurrencyLogDataProvider(repo: ServiceRepository): CurrencyLogDataProvider {
        return CurrencyLogDataProviderImpl(repo)
    }

    @Provides
    fun provideCurrencyLogDataRepository(dataProvider: CurrencyLogDataProvider): CurrencyLogRepository {
        return CurrencyLogRepositoryImpl(dataProvider)
    }

    @Provides
    fun provideCurrencyExchangeDataProvider(repo: ServiceRepository): CurrencyExchangeDataProvider {
        return CurrencyExchangeDataProviderImpl(repo)
    }

    @Provides
    fun provideCurrencyExchangeDataRepository(dataProvider: CurrencyExchangeDataProvider): CurrencyExchangeRepository {
        return CurrencyExchangeRepositoryImpl(dataProvider)
    }

}