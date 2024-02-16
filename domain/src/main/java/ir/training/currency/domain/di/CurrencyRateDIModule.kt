package ir.training.currency.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.training.currency.domain.repository.currency.item.CurrencyRateRepository
import ir.training.currency.domain.usecase.currency.item.CurrencyRateUseCase

@Module
@InstallIn(SingletonComponent::class)
class CurrencyRateDIModule {
    @Provides
    fun provideCurrencyRateUseCase(repository: CurrencyRateRepository): CurrencyRateUseCase {
        return CurrencyRateUseCase(repository)
    }
}