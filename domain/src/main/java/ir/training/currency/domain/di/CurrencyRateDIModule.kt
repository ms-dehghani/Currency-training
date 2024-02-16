package ir.training.currency.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.training.currency.domain.repository.currency.item.CurrencyRateRepository
import ir.training.currency.domain.repository.currency.log.CurrencyLogRepository
import ir.training.currency.domain.usecase.currency.item.CurrencyRateUseCase
import ir.training.currency.domain.usecase.currency.log.add.CurrencyLogAddUseCase
import ir.training.currency.domain.usecase.currency.log.list.CurrencyLogListUseCase

@Module
@InstallIn(SingletonComponent::class)
class CurrencyRateDIModule {
    @Provides
    fun provideCurrencyRateUseCase(repository: CurrencyRateRepository): CurrencyRateUseCase {
        return CurrencyRateUseCase(repository)
    }

    @Provides
    fun provideCurrencyLogListUseCase(repository: CurrencyLogRepository): CurrencyLogListUseCase {
        return CurrencyLogListUseCase(repository)
    }

    @Provides
    fun provideCurrencyLogAddUseCase(repository: CurrencyLogRepository): CurrencyLogAddUseCase {
        return CurrencyLogAddUseCase(repository)
    }
}