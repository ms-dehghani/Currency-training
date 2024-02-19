package ir.training.currency.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.training.currency.domain.repository.currency.item.exchange.CurrencyExchangeRepository
import ir.training.currency.domain.repository.currency.item.rate.CurrencyRateRepository
import ir.training.currency.domain.repository.currency.log.CurrencyLogRepository
import ir.training.currency.domain.repository.wallet.WalletRepository
import ir.training.currency.domain.usecase.currency.item.exchange.CurrencyExchangeUseCase
import ir.training.currency.domain.usecase.currency.item.rate.CurrencyRateUseCase
import ir.training.currency.domain.usecase.currency.log.add.CurrencyLogAddUseCase
import ir.training.currency.domain.usecase.currency.log.list.CurrencyLogListUseCase
import ir.training.currency.domain.usecase.wallet.WalletUseCase

@Module
@InstallIn(SingletonComponent::class)
class DIModule {
    @Provides
    fun provideCurrencyRateUseCase(repository: CurrencyRateRepository): CurrencyRateUseCase {
        return CurrencyRateUseCase(repository)
    }

    @Provides
    fun provideCurrencyExchangeUseCase(repository: CurrencyExchangeRepository): CurrencyExchangeUseCase {
        return CurrencyExchangeUseCase(repository)
    }


    @Provides
    fun provideCurrencyLogListUseCase(repository: CurrencyLogRepository): CurrencyLogListUseCase {
        return CurrencyLogListUseCase(repository)
    }

    @Provides
    fun provideCurrencyLogAddUseCase(repository: CurrencyLogRepository): CurrencyLogAddUseCase {
        return CurrencyLogAddUseCase(repository)
    }

    @Provides
    fun provideWalletUseCase(repository: WalletRepository): WalletUseCase {
        return WalletUseCase(repository)
    }

}