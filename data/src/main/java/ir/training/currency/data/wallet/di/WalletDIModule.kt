package ir.training.currency.data.wallet.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.training.currency.data.ServiceRepository
import ir.training.currency.data.wallet.rate.dataprovider.WalletDataProvider
import ir.training.currency.data.wallet.rate.dataprovider.WalletDataProviderImpl
import ir.training.currency.data.wallet.rate.repository.WalletRepositoryImpl
import ir.training.currency.domain.repository.wallet.WalletRepository

@Module
@InstallIn(SingletonComponent::class)
internal class WalletDIModule {

    @Provides
    fun provideWalletProvider(repo: ServiceRepository): WalletDataProvider {
        return WalletDataProviderImpl(repo)
    }

    @Provides
    fun provideWalletRepository(dataProvider: WalletDataProvider): WalletRepository {
        return WalletRepositoryImpl(dataProvider)
    }

}